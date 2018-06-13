package com.zou.common;


import com.zou.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    Logger logger = LoggerFactory.getLogger("HandlerExceptionResolver");


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        String url = request.getRequestURL().toString();

        ModelAndView modelAndView;

        String defaultMsg = "System error";

        // json  page
        // 要求项目中所有请求数据  都使用.json结尾
        if (url.endsWith(".json")) {


            if (ex instanceof PermissionException) {


                logger.info("unknow exception: " + url, ex);

                JsonData result = JsonData.fail(ex.getMessage());
                modelAndView = new ModelAndView("jsonView", result.toMap());


            } else {
                logger.info("unknow exception: " + url, ex);
                JsonData result = JsonData.fail(defaultMsg);

                modelAndView = new ModelAndView("jsonView", result.toMap());
            }

            // 要求项目中所有请求page页面数据  都使用.page结尾
        } else if (url.endsWith(".page")) {

            logger.info("unknow page exception: " + url, ex);
            JsonData result = JsonData.fail(defaultMsg);
            modelAndView = new ModelAndView("exception", result.toMap());

        } else {
            logger.info("unknow json exception: " + url, ex);

            JsonData result = JsonData.fail(defaultMsg);
            modelAndView = new ModelAndView("jsonView", result.toMap());

        }


        return modelAndView;
    }
}





































