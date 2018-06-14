package com.zou.controller;

import com.zou.common.JsonData;
import com.zou.param.DeptParam;
import com.zou.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    Logger logger = LoggerFactory.getLogger(SysDeptController.class);

    @Resource
    private SysDeptService sysDeptService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param) {


        sysDeptService.save(param);

        return JsonData.success();
    }


}




























































