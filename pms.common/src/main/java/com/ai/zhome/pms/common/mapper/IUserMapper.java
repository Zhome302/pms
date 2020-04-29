package com.ai.zhome.pms.common.mapper;

import com.ai.zhome.pms.common.model.IUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface IUserMapper {

    @Select("select * from I_USER where userID = #{userID, jdbcType=VARCHAR}")
    IUser getUserInfo(String userID);

    @Select("select * from I_USER limit 1")
    IUser getAllUserInfo();


}
