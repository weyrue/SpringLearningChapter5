package com.zy.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/miya")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        System.out.println("hello miya!");
        return "index.jsp";
    }
}
