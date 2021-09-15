package study.springcloud.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 汶泽
 * @date 2021/8/7-22:08
 */
@Configuration
public class SentinelConfig {
    /**
     * 注解支持的配置Bean
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }
}
