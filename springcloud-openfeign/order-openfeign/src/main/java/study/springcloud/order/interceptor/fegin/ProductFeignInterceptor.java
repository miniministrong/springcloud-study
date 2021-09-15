package study.springcloud.order.interceptor.fegin;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dhl
 * @datetime 2021/8/6  10:57
 */
public class ProductFeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // todo
        // 修改请求头中的信息
        // requestTemplate.header("", "");
        // 修改参数
        // requestTemplate.query("id", "");
        // 修改路径
        requestTemplate.uri("/get/3");
        logger.info("feign拦截器！");
    }
}