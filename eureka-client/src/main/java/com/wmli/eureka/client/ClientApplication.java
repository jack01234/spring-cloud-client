package com.wmli.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 启动入口
 *
 * @author yingmuhuadao
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class,args);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/hello")
    public String helloWorld(HttpServletRequest request){
        System.out.println("hello world!!!!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+request.getHeader("X-Request-Foo"));
        return "hello world";
    }
}
