package com.ai.zhome.pms.practice.controller.content;

import com.ai.zhome.pms.practice.model.UserStudy;
import com.ai.zhome.pms.practice.service.JavaCourse;

public class StudyController {

    public static void main(String[] args){
        UserStudy user = new UserStudy();
        user.setUserName("小明");
        user.setCourse(new JavaCourse());
        System.out.println(user.getStudyInfo());
    }
}
