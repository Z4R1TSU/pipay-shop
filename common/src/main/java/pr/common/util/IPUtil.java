package pr.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class IPUtil {

    public static String getIP(HttpServletRequest request) {
        if (request == null) {
            return "未知";
        }
        
        String ip = request.getHeader("X-Forwarded-For");
        if (isInvalidIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isInvalidIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isInvalidIP(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isInvalidIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (isInvalidIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean isInvalidIP(String ip) {
        return ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip);
    }

    public static String getIP() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return "未知";
        }
        return getIP(attributes.getRequest());
    }

    public static String Ip2Region(String ip) {
        if ("127.0.0.1".equals(ip) || ip.startsWith("192.168")) {
            return "局域网 ip";
        }
        // TODO: 待实现，因为原先项目中使用了外部文件
        return "待实现";
    }
}
