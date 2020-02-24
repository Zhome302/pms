package com.ai.zhome.pms.practice.service;

import com.ai.zhome.pms.practice.service.intf.ICourse;

public class PythonCourse implements ICourse {

    @Override
    public String study(){
        String learn = "正在Python学习";
        return learn;
    }

}
