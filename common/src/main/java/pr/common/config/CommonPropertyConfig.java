package pr.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class CommonPropertyConfig {
    // 如果需要，可以在这里添加一些通用的bean定义
}