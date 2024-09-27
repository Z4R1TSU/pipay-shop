package pr.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import pr.common.dto.LoginDTO;
import pr.common.util.Constants;
import pr.common.util.IPUtil;
import pr.common.util.JWTUtil;
import pr.common.util.RedisUtil;
import pr.common.vo.Result;
import pr.user.entity.UserInfo;
import pr.user.service.UserInfoService;
import pr.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
* @author 30786
* @description 针对表【user_info(用户数据表)】的数据库操作Service实现
* @createDate 2024-09-12 15:53:48
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Resource
    private UserInfoMapper userInfoMapper;
    
    @Resource
    private RedisUtil redisUtil;
    
    @Resource
    private JWTUtil jwtUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result login(LoginDTO loginDTO) {
        // 1. 确定验证码是否正确
        String username = loginDTO.getUserName();
        String accessToken = loginDTO.getAccessToken();
        String captcha = loginDTO.getCaptcha();
        Object rawCaptcha = redisUtil.get(Constants.REDIS_CAPTCHA_PREFIX + username);
        if (rawCaptcha == null) {
            return Result.fail("验证码已过期或不存在");
        }
        String captchaInRedis = rawCaptcha.toString();
        if (!captcha.equals(captchaInRedis)) {
            return Result.fail("验证码错误");
        }
        // 2. 确定用户是否存在
        UserInfo userInfo = getOne(
                new LambdaQueryWrapper<UserInfo>()
                        .eq(UserInfo::getUserName, username));
        if (userInfo == null) {
            // 3.1. 注册新用户
            UserInfo newUserInfo = new UserInfo();
            // 3.2. 设置用户属性
            newUserInfo.setPiName(username);
            newUserInfo.setUserName(username);
            // 3.2.1. 创建用户token
            String token = jwtUtil.generateToken(username);
            newUserInfo.setAccessToken(token);
            newUserInfo.setUid(username);
            newUserInfo.setUserImage(null);
            // 3.3. 保存用户到数据库
            save(newUserInfo);
            // 3.4. 保存用户token到redis
            boolean set = redisUtil.set(Constants.REDIS_LOGIN_TOKEN_PREFIX + username, token, Constants.REDIS_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            if (!set) {
                return Result.fail("保存token到redis失败");
            }
        } else {
            // 4. 若用户已存在，则校验token是否正确
            Object rawToken = redisUtil.get(Constants.REDIS_LOGIN_TOKEN_PREFIX + username);
            if (rawToken == null) {
                return Result.fail("该用户不存在token");
            }
            String token = rawToken.toString();
            // 5. 若token不匹配，则显示登录失败
            if (!accessToken.equals(token)) {
                return Result.fail("token错误");
            }
        }
        // 6. 记录登录信息
        String ip = IPUtil.getIP();
        return Result.success(username);
    }
    
    @Override
    public Result generateCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");
            response.setHeader("sessionID", "1234");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.close();
            redisUtil.set(Constants.REDIS_CAPTCHA_PREFIX + "test888", "1234", Constants.REDIS_CAPTCHA_EXPIRE_TIME, TimeUnit.SECONDS);
            return Result.success();
        } catch (IOException e) {
            return Result.fail(e.getMessage());
        }
    }
    
}




