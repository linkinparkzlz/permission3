package com.zou.service;


import com.zou.dao.SysDeptMapper;
import com.zou.exception.ParamException;
import com.zou.model.SysDept;
import com.zou.param.DeptParam;
import com.zou.util.BeanValidator;
import com.zou.util.LevelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param) {

        BeanValidator.check(param);
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept dept = new SysDept(param.getName(), param.getParentId(), param.getSeq(), param.getRemark());

        dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));


        dept.setOperator("system"); // TODO
        dept.setOperateIp("127.0.0.1"); //  TODO
        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);

    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {

        // TODO
        return true;
    }


    private String getLevel(Integer deptId) {

        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {

            return null;
        }
        return dept.getLevel();

    }


}






































