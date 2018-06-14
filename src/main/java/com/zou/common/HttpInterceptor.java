package com.zou.common;

import com.zou.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    private static final String START_TIME = "requestStartTime";

    //请求处理之前的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String url = request.getRequestURL().toString();

        Map parameterMap = request.getParameterMap();

        logger.info("request start url:{},params:{}", url, JsonMapper.object2String(parameterMap));

        return true;
    }


    // 请求正常结束的时候调用的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


        String url = request.getRequestURL().toString();

//        Map parameterMap = request.getParameterMap();
//
//        logger.info("request finished url:{},params:{}", url, JsonMapper.object2String(parameterMap));


        long start = (Long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        logger.info("request  finished  url:{},cost:{}", url, end - start);


    }

    //任何时候请求结束调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String url = request.getRequestURL().toString();

        Map parameterMap = request.getParameterMap();

        logger.info("exceptiopn  url:{},params:{}", url, JsonMapper.object2String(parameterMap));
    }
}































