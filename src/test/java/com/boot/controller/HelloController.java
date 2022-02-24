
package com.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController  //@Controller和ResponseBody的复合注解,让此类成为控制器,并且方法返回都是直接打印到浏览器
public class HelloController {

    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, Spring Boot 2!";
    }

    @RequestMapping(value = "iii")
    public String handle02(){
        return "Hello, Spring Boot 2!";
    }
}
