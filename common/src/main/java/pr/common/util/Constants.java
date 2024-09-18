package pr.common.util;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String REDIS_LOGIN_TOKEN_PREFIX = "login_token_";
    
    public static final Long REDIS_TOKEN_EXPIRE_TIME = 8 * 60 * 60 * 1000L; // 8 hours

}
