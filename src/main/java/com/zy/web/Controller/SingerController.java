package com.zy.web.Controller;

import com.zy.web.entities.SingerPO;
import com.zy.web.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/singers")
@Controller
public class SingerController {
    @Autowired
    private SingerService singerService;
    private MessageSource messageSource;

    @RequestMapping(method=RequestMethod.GET)
    public String list(Model uiModel){
        System.out.println("Listing singers");
        List<SingerPO> singers = singerService.findAll();
        uiModel.addAttribute("singers",singers);
        System.out.println("No. of singers: "+singers.size());
        return "singers/list";
    }
}
