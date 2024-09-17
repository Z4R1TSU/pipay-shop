package pr.common.dto;

import lombok.Data;

@Data
public class LoginDTO {

    // 用户名
    private String username;

    // 密码
    private String password;

    // 验证码
    private String captcha;

}
