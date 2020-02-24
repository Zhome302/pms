package com.ai.zhome.pms.practice.model;

import com.ai.zhome.pms.practice.service.intf.ICourse;

public class UserStudy {

    private String userName;

    private ICourse course;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public String getStudyInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append(userName);
        builder.append(course.study());
        return builder.toString();
    }
}
