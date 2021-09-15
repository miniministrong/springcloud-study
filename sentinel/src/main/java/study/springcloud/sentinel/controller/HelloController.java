package study.springcloud.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springcloud.sentinel.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 汶泽
 * @date 2021/8/7-21:40
 */
@RestController
@Slf4j
public class HelloController {
    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    /**
     * 进行sentinel流控
     */
    @RequestMapping("/hello")
    public String hello(){
        Entry entry = null;
        try {
            // sentinel针对资源进行限制的
            entry = SphU.entry(RESOURCE_NAME);
            // 被保护的业务逻辑
            String str = "hello world";
            log.info("===========" + str + "============");
            return str;
        } catch (BlockException e) {
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理操作
            log.info("block!");
            return "被流控了！";
        } catch (Exception e) {
            // 若要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e, entry);
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    /**
     * 定义规则
     * spring的初始化方法
     */
    @PostConstruct
    private static void initFlowRules(){
        // 流控规则
        List<FlowRule> rules = new ArrayList<>();
        // 流控
        FlowRule rule = new FlowRule();
        // 设置受保护的资源(为哪个资源进行流控)
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则的QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        // Set limit QPS to 20
        rule.setCount(1);
        rules.add(rule);

        // 通过@SentinelResource来定义资源并配置降级和流控的处理方法
        FlowRule r = new FlowRule();
        // 设置受保护的资源
        r.setResource(USER_RESOURCE_NAME);
        // 设置流控规则QPS
        r.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        r.setCount(1);
        rules.add(r);

        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * @SentinelResource 改善接口中资源定义和被流控降级后的处理方法
     * 怎么使用：
     *  1、添加依赖：<artifactId>sentinel-annotation-aspectj</artifactId>
     *  2、配置bean：SentinelResourceAspect
     *  value：定义资源
     *  blockHandler：设置流控降级后的处理方法（默认该方法必须声明在同一个类中）
     *      如果不想在同一个类，可以添加blockHandlerClass来声明你将这个处理异常的方法放在了哪个类中，注意：如果放在了其他类上，必须添加static关键字
     *  fallback：当接口出现了异常就可以交给fallback指定的方法进行处理
     *      如果不想在同一个类中。可以添加fallbackClass来指定
     *  blockHandler如果和fallback同时指定了，则blockHandler优先级更高
     *  exceptionsToIgnore：出现指定的异常不会进行处理
     */
    @RequestMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME,
            blockHandler = "blockHandlerForGetUser",
            fallback = "fallbackHandlerForGetUser",
            exceptionsToIgnore = {
                ArithmeticException.class
            })
    public User getUser(String id) {
        int a = 1 / 0;
        return new User("wenze");
    }

    public User fallbackHandlerForGetUser(String id, Throwable e){
        e.printStackTrace();
        return new User("异常处理");
    }

    /**
     * 注意：
     *  1、一定要public
     *  2、返回值一定要和原方法保持一致，包含原方法的参数
     *  3、可以在参数最后添加BlockException的异常类 可以区分是什么规则的处理方法
     */
    public User blockHandlerForGetUser(String id, BlockException e) {
        e.printStackTrace();
        return new User("流控！！！");
    }


    /**
     * 降级规则初始化
     */
    @PostConstruct
    public void initDegradeRule(){
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        // 设置规律策略：异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 触发熔断异常数：2
        degradeRule.setCount(2);
        // 触发熔断最小请求数：2
        degradeRule.setMinRequestAmount(2);
        // 统计时长：1分钟，默认1s，单位ms
        degradeRule.setStatIntervalMs(60 * 1000);
        // 一分钟内执行两次，出现了两次异常，就会触发熔断
        // 熔断持续时长：10s
        // 一旦触发了熔断，再次请求对应的接口就会直接调用降级方法
        // 10s过了之后，会变成一个半开状态：恢复接口请求调用，如果第一次请求就异常，那么就会再次熔断，不会再根据设置熔断条件来判断
        degradeRule.setTimeWindow(10);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }

    @RequestMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN, blockHandler = "blockHandlerForFb")
    public User degrade(String id) throws InterruptedException{
        throw new RuntimeException("异常");
    }

    public User blockHandlerForFb(String id, BlockException e){
        return new User("熔断降级");
    }

}
