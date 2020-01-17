package com.ai.zhome.pms.commom.mapper;

import com.ai.zhome.pms.commom.model.IUser;
import org.apache.ibatis.annotations.Select;

public interface IUserMapper {

    @Select("select * from I_USER where userID = #{userID, jdbcType=VARCHAR}")
    IUser getUserInfo(String userID);


}
