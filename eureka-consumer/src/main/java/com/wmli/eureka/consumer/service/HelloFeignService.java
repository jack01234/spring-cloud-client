package com.wmli.eureka.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign 测试类
 *
 * @author yingmuhuadao
 * @date 2019/4/19
 */
@FeignClient(name = "EUREKA-CLIENT")
public interface HelloFeignService {
    /**
     * 访问hello
     *
     * @return 结果
     */
    @GetMapping(value = "/hello")
    String helloWorld();
}
