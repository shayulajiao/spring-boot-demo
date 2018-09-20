package com.hewc.Service;

import com.hewc.mapper.RoleMapper;
import com.hewc.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    public int deleteByPrimaryKey(Long id){
        return roleMapper.deleteByPrimaryKey(id);
    }

    public int insert(Role record){
        return roleMapper.insert(record);
    }

    public int insertSelective(Role record){
        return roleMapper.insertSelective(record);
    }

    public Role selectByPrimaryKey(Long id){
        return roleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Role record){
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Role record){
        return roleMapper.updateByPrimaryKey(record);
    }

    public List<Role> findAllRolesByName(String userId){
        return roleMapper.findAllRolesByName(userId);
    }
}
