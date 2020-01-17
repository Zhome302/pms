package com.ai.zhome.pms.practice.controller.systemManage;

import com.ai.zhome.pms.commom.mapper.IUserMapper;
import com.ai.zhome.pms.commom.model.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserMapper userMapper;

    @PostMapping("/check")
    public @ResponseBody String checkUser(HttpServletRequest request) {
        String userID = request.getParameter("userID");
        IUser userInfo = null;
        String result = "用户校验成功！";
        if(userID == null || userID.isEmpty()){
            result = "用户名为空,校验失败！";
        }else{
            userInfo = userMapper.getUserInfo(userID);
        }
        if(userInfo == null){
            result = "用户名不存在,校验失败！";
        }
        return result;
    }
}
