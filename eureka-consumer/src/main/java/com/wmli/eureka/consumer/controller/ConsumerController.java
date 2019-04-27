package com.wmli.eureka.consumer.controller;

import com.wmli.eureka.consumer.service.HelloFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 消费者controller
 *
 * @author yingmuhuadao
 * @date 2019/4/17
 */
@Slf4j
@RestController
public class ConsumerController {
    /**
     * 请求模板
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * feign调用服务
     */
    @Autowired
    private HelloFeignService helloFeignService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(HttpServletRequest request){
        log.info(">>>>>>>>>>>>>>helloConsumer<<<<<<<<<<<<<<");
        log.info("headers ================ > {}",request.getHeader("X-Request-Foo"));
        return restTemplate.getForEntity("http://EUREKA-CLIENT/hello",String.class).getBody();
    }

    @GetMapping(value = "/hello")
    public String hello(){
        log.info(" helloFeignService ");
        return helloFeignService.helloWorld();
    }
}
