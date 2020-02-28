package com.zy.web.controller;

import com.zy.web.entities.SingerPO;
import com.zy.web.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//此处拦截的路径应该去掉DispatcherServlet设置的路径
//@RequestMapping("/rest")
public class SingerController {
    @Autowired
    private SingerService singerService;

    //此处拦截
    @RequestMapping("/singers")
    @ResponseBody
    public List<SingerPO> list(SingerPO singerPO) {
        System.out.println("firstName: " + singerPO.getFirstName());
        System.out.println("LastName: " + singerPO.getLastName());
        System.out.println("Listing singers");
        List<SingerPO> singers = singerService.findAll();
//        uiModel.addAttribute("singers", singers);
        return singers;
    }
}
