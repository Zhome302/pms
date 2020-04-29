package com.ai.zhome.pms.portal.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal")
public class LoginController {

    @GetMapping("/login")
    public String index(){
        return "";
    }


}
