package com.ai.zhome.pms.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("index")
    public String index(){
        return "";
    }

    @GetMapping("test")
    public void testIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getQueryString();
        String result = String.format("欢迎来到ZHome项目管理系统,测试成功.参数[%s]",param);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(result);
    }


}
