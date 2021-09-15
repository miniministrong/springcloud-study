package study.springcloud.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dhl
 * @datetime 2021/8/6  9:48
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/get/{id}")
    public String get(@PathVariable Integer id){
        System.out.println("查询商品" + id);
        return "查询商品" + id + " : " + port;
    }
}