package study.springcloud.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;
import study.springcloud.order.service.OrderService;

/**
 * @author 汶泽
 * @date 2021/8/8-19:43
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    @SentinelResource(value = "getUser", blockHandler = "blockHandlerGetUser")
    public String getUser() {
        return "查询用户";
    }

    public String blockHandlerGetUser(BlockException e) {
        return "流控了！！！！！";
    }
}
