package pr.common.util;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class JWTUtil {

    // 硬编码密钥
    private static final String SECRET_KEY = "PaiRuan-Shop";

//    private static final SecretKey JWT_KEY;
//
//    // 这是使用static块来初始化密钥的，这样可以保证密钥只被初始化一次，避免多次生成密钥对象
//    static {
//        byte[] encodeKey = Base64.getDecoder().decode(SECRET_KEY);
//        JWT_KEY = Keys.hmacShaKeyFor(encodeKey); // 使用 HMAC-SHA 算法生成密钥
//    }

    // 生成token
    public static String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Constants.REDIS_TOKEN_EXPIRE_TIME);
        Map<String, Object> payload = new HashMap<>();
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, expiryDate);
        payload.put(JWTPayload.NOT_BEFORE, now);
        payload.put("username", username);
        return cn.hutool.jwt.JWTUtil.createToken(payload, SECRET_KEY.getBytes());
    }

    // 根据token获取用户名
    public static JSONObject parseToken(String token) {
        JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(SECRET_KEY.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        return payloads;
    }

    // 验证token并返回结果
    public static TokenValidationResult validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return new TokenValidationResult(false, "令牌为空");
        }

        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return new TokenValidationResult(false, "令牌格式不正确，应包含三个部分");
        }

        try {
            // 验证头部
            String header = new String(Base64.getDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            if (!header.contains("\"alg\":") || !header.contains("\"typ\":")) {
                return new TokenValidationResult(false, "令牌头部格式不正确");
            }

            // 验证载荷
            String payload = new String(Base64.getDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            if (!payload.contains("\"exp\":")) {
                return new TokenValidationResult(false, "令牌载荷缺少过期时间");
            }

            // 验证签名
            boolean verify = cn.hutool.jwt.JWTUtil.verify(token, SECRET_KEY.getBytes());
            if (!verify) {
                return new TokenValidationResult(false, "令牌签名无效");
            }

            // 验证过期时间
            JWT jwt = cn.hutool.jwt.JWTUtil.parseToken(token).setKey(SECRET_KEY.getBytes());
            if (!jwt.validate(0)) {
                return new TokenValidationResult(false, "令牌已过期");
            }

            return new TokenValidationResult(true, "令牌有效");
        } catch (Exception e) {
            return new TokenValidationResult(false, "令牌验证失败: " + e.getMessage());
        }
    }

    // 用于返回验证结果的内部类
    public static class TokenValidationResult {
        private final boolean valid;
        private final String message;

        public TokenValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }
}