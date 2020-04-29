package com.ai.zhome.pms.content.service;

import com.ai.zhome.pms.common.mapper.IUserMapper;
import com.ai.zhome.pms.common.model.IUser;
import com.ai.zhome.pms.common.slaveMapper.SlaveIUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserMapper iUserMapper;

    @Autowired
    private SlaveIUserMapper slaveIUserMapper;

    public IUser getAllUser(){
        IUser user  = iUserMapper.getAllUserInfo();
        return  user;
    }

    public IUser getSlaveAllUser(){
        IUser user  = slaveIUserMapper.getAllUserInfo();
        return  user;
    }


}
