package pr.common.dto;

import lombok.Data;

@Data
public class LoginDTO {
    
    private String userName;
    
    private String accessToken;
    
    private String captcha;
    
}
