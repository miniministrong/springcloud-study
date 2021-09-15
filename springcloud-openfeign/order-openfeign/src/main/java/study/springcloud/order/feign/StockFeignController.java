package study.springcloud.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dhl
 * @datetime 2021/8/6  9:32
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockFeignController {
    @RequestMapping("/reduce")
    String reduce();
}
