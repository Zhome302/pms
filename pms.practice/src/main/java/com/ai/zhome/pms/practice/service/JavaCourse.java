package com.ai.zhome.pms.practice.service;

import com.ai.zhome.pms.practice.service.intf.ICourse;

public class JavaCourse implements ICourse {

    @Override
    public String study(){
        String learn = "正在Java学习";
        return learn;
    }

}
