package com.hewc.Service;

import com.hewc.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService {

    @Resource
    private ResourceMapper resourceMapper;

    public List<String> findResourceByRole(Long roleId){
        return resourceMapper.findResourceByRole(roleId);
    }
}
