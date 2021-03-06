package test.mass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableScheduling
@ComponentScan(basePackages = {"com.mass"})
@MapperScan("com.mass.dao")
public class TestApplication implements ApplicationContextAware {


    /**
     * Start application.
     *
     * @param args 启动传入参数
     */
    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    /**
     * 解决 Feignclient与springfox Swagger的集成冲突问题, 在不升级springfox的前提下，使用workaround.
     * <p>
     * www.github.com/springfox/springfox/issues/1074
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // force the bean to get loaded as soon as possible
        applicationContext.getBean("requestMappingHandlerAdapter");
    }
}
