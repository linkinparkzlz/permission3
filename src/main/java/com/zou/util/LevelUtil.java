package com.zou.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    //各个层级的分隔符
    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    // level的计算规则
    public static String calculateLevel(String parentLevel, int parentId) {

        //如果是首层
        if (StringUtils.isBlank(parentLevel)) {

            return ROOT;

            //不是首层
        } else {

            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }


    }


}




























