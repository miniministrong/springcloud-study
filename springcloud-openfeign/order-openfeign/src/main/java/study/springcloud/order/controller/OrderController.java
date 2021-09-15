package study.springcloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springcloud.order.feign.ProductFeignController;
import study.springcloud.order.feign.StockFeignController;

/**
 * @author dhl
 * @datetime 2021/8/6  9:10
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private StockFeignController stockFeignController;
    @Autowired
    private ProductFeignController productFeignController;
    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功");
        String msg = stockFeignController.reduce();
        String p = productFeignController.get(1);
        return "Hello World" + msg + p;
    }
}