package study.springcloud.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springcloud.order.service.OrderService;

import java.util.concurrent.TimeUnit;

/**
 * @author 汶泽
 * @date 2021/8/8-18:07
 */
@RestController
@RequestMapping("/order")
public class OrderSentinelController {
    @RequestMapping("/add")
    public String add() {
        return "Hello Sentinel";
    }

    @RequestMapping("/flow")
    // @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flow() {
        return "正常访问";
    }

    public String flowBlockHandler(BlockException e) {
        return "流控！！！";
    }

    @RequestMapping("/flowThread")
    @SentinelResource(value = "flowThread", blockHandler = "flowThreadBlockHandler")
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "正常访问";
    }

    public String flowThreadBlockHandler(BlockException e) {
        return "流控！！！";
    }

    @Autowired
    OrderService orderService;

    @RequestMapping("/test1")
    public String test1(){
        return orderService.getUser();
    }

    @RequestMapping("/test2")
    public String test2(){
        return orderService.getUser();
    }
}
