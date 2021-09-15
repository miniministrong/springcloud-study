package study.springcloud.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dhl
 * @datetime 2021/8/5  14:48
 */
@Configuration
public class RibbonRandomRuleConfig {
    /**
     * 方法名称一定要叫iRule
     * 当前配置类一定不要放在@ComponentScan注解能够扫描到的地方
     * @return
     */
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}