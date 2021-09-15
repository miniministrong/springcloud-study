package study.springcloud.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 汶泽
 * @date 2021/8/8-22:35
 */
@Component
@FeignClient(value = "stock-service", path = "/stock", fallback = StockFeignControllerFallback.class)
public interface StockFeignController {
    @RequestMapping("/reduct")
    String reduct();
}
