package pr.common.config;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pr.common.util.Constants;
import pr.common.util.JWTUtil;
import pr.common.util.RedisUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlankIfStr(token)) {
            response.sendRedirect("/login");
            return false;
        }

        JWTUtil.TokenValidationResult result = jwtUtil.validateToken(token);
        if (!result.isValid()) {
            response.sendRedirect("/login");
            return false;
        }

        String username = jwtUtil.parseToken(token).getStr("username");
        Object storedToken = redisUtil.get(Constants.REDIS_LOGIN_TOKEN_PREFIX + username);
        if (storedToken == null || !token.equals(storedToken.toString())) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}