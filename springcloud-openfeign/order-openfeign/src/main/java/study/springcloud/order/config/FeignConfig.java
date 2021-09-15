package study.springcloud.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.springcloud.order.interceptor.fegin.ProductFeignInterceptor;

/**
 * @author dhl
 * @datetime 2021/8/6  9:53
 */
// @Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public ProductFeignInterceptor productFeignInterceptor(){
        return new ProductFeignInterceptor();
    }
}