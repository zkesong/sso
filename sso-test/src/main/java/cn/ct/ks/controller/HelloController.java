package cn.ct.ks.controller;

import cn.ct.ks.service.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference(version = "1.0.0")
    private HelloService service;

    @GetMapping("hello")
    public String hello(@RequestParam String name){
        return service.sayHello(name);
    }
}
