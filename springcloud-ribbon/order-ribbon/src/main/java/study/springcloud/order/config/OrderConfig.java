package study.springcloud.order.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import study.springcloud.ribbon.RibbonRandomRuleConfig;

/**
 * @author dhl
 * @datetime 2021/8/4  14:26
 */
@Configuration
/*@RibbonClients(value = {
        @RibbonClient(name = "stock-service", configuration = RibbonRandomRuleConfig.class)
})*/
public class OrderConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}