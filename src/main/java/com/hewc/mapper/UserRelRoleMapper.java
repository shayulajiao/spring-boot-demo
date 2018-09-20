package com.hewc.mapper;

import com.hewc.model.UserRelRole;

public interface UserRelRoleMapper {
    int insert(UserRelRole record);

    int insertSelective(UserRelRole record);
}