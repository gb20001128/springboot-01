package com.boot.controller;
//这是一个接收web层参数并执行对应功能的类
import com.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j           //注入lombok里面的日志类
@RestController //@Controller和ResponseBody的复合注解,让此类成为控制器,并且方法返回值都是直接打印到浏览器
public class HelloController {

    @Autowired
    private Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }


    @RequestMapping("/hello")
     public String handle01() {
        log.info("请求进来了");   //日志记录
        return "Hello SpringBoot 2!";
    }


}
