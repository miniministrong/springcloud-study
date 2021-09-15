package study.springcloud.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dhl
 * @datetime 2021/8/4  11:13
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @RequestMapping("/reduce")
    public String reduce(){
        System.out.println("扣减库存");
        return "扣减库存";
    }
}