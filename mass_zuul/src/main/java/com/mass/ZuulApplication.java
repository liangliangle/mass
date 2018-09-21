package com.mass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * .
 *
 */
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
public class ZuulApplication {

  @Bean
  public AuthorizationFilter simpleFilter() {
    return new AuthorizationFilter();
  }

  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }
}
