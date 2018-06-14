package com.zou.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一个类转换成一个Json对象；
 * 将Json转换为类对象
 */

@Slf4j
public class JsonMapper {

    public static Logger logger = LoggerFactory.getLogger(JsonMapper.class);


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {

        //遇到不知道的字段时
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);


    }


    //  对象转换为字符串
    public static <T> String object2String(T src) {

        if (src == null) {
            return null;
        }

        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {

            logger.info("parse   object  to String exception", e);
            return null;

        }
    }

    //字符串转为对象

    public static <T> T string2Object(String src, TypeReference<T> tTypeReference) {

        if (src == null || tTypeReference == null) {
            return null;
        }
        try {
            return (T) (tTypeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, tTypeReference));
        } catch (Exception e) {

            logger.warn("parse String to Object exception");
            return null;

        }
    }


}



















































