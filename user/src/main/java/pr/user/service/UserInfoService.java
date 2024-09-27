package pr.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pr.common.dto.LoginDTO;
import pr.common.vo.Result;
import pr.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30786
* @description 针对表【user_info(用户数据表)】的数据库操作Service
* @createDate 2024-09-12 15:53:48
*/
public interface UserInfoService extends IService<UserInfo> {

    Result login(LoginDTO loginDTO);
    
    Result generateCaptcha(HttpServletRequest request, HttpServletResponse response);
}
