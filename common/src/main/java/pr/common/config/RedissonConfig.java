package pr.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // TODO: 暂时设置了单点Redis，之后可考虑部署集群
        // TODO: 可使用setPassword方法设置密码
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
//                .setPassword("123456");
        return Redisson.create(config);
    }

}
