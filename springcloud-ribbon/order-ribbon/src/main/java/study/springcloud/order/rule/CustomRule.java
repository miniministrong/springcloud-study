package study.springcloud.order.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dhl
 * @datetime 2021/8/5  16:22
 */
public class CustomRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        // 获取当前请求的服务的实例
        List<Server> reachableServers = loadBalancer.getReachableServers();
        // 获得当前线程的随机数（nestInt：给一个随机数的范围）
        int random = ThreadLocalRandom.current().nextInt(reachableServers.size());
        // 将下标传给服务
        Server server = reachableServers.get(random);
        // 判断：如果当前服务是否存活
        if (!server.isAlive()) {

        }
        return server;
    }
}