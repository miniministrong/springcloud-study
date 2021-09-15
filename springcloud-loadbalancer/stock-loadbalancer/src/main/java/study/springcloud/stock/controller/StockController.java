package study.springcloud.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dhl
 * @datetime 2021/8/5  17:18
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Value("${server.port}")
    String port;
    @RequestMapping("/reduce")
    public String reduce(){
        System.out.println("扣减库存" + port);
        return "扣减库存" + port;
    }
}