package study.springcloud.order.feign;

import org.springframework.stereotype.Component;

/**
 * @author 汶泽
 * @date 2021/8/8-22:48
 */
@Component
public class StockFeignControllerFallback implements StockFeignController {
    @Override
    public String reduct() {
        return "降级了！";
    }
}
