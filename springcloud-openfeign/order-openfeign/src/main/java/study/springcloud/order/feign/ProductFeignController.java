package study.springcloud.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.springcloud.order.config.FeignConfig;

/**
 * @author dhl
 * @datetime 2021/8/6  9:55
 */
@FeignClient(name = "product-service", path = "/product", configuration = FeignConfig.class)
public interface ProductFeignController {
    @RequestMapping("/get/{id}")
    String get(@PathVariable("id") Integer id);
}
