package com.ai.zhome.pms.content.ctrl;

import com.ai.zhome.pms.content.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/content")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void index(HttpServletRequest request, HttpServletResponse response){
        try{
            StringBuilder builder = new StringBuilder();
            builder.append(userService.getAllUser().getUserName());
            builder.append(userService.getSlaveAllUser().getUserName());
            response.getWriter().println(builder.toString());

        }catch (Exception e){
            e.printStackTrace();

        }

    }


}
