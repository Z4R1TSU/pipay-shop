package pr.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
@Slf4j
public class JWTUtil {

    // 设定过期时间为8小时
    private static final Long JWT_TTL = 8 * 60 * 60 * 1000L;

    // 设定密钥
    private static final String JWT_SECRET = "pr-secret";

    private static final SecretKey JWT_SECRET_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    // 生成token
    public static String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_TTL);

        return Jwts.builder()
               .subject(username)
               .issuedAt(now)
               .expiration(expiryDate)
               .signWith(JWT_SECRET_KEY)
               .compact();
    }

    // 根据token获取用户名
    public static String parseToken(String token) {
        return Jwts.parser()
               .verifyWith(JWT_SECRET_KEY)
               .build()
               .parseSignedClaims(token)
               .getPayload()
               .getSubject();
    }

    // 验证token是否有效
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                   .verifyWith(JWT_SECRET_KEY)
                   .build()
                   .parseSignedClaims(token);
            
            // 检查token是否过期
            Date expiration = Jwts.parser()
                .verifyWith(JWT_SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
            
            return new Date().before(expiration);
        } catch (ExpiredJwtException e) {
            log.warn("Token已过期: {}", e.getMessage());
        } catch (SignatureException e) {
            log.warn("无效的JWT签名: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("无效的JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("不支持的JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims字符串为空: {}", e.getMessage());
        }
        return false;
    }

}
