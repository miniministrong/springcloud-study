package study.springcloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dhl
 * @datetime 2021/8/4  14:24
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/add")
    public String add(){
        System.out.println("δΈεζε");
        String msg = restTemplate.getForObject("http://stock-service/stock/reduce", String.class);
        return "Hello World" + msg;
    }
}