package com.zou.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zou.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {

    public static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {

        Validator validator = validatorFactory.getValidator();

        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {

            return Collections.emptyMap();
        } else {
            LinkedHashMap errors = Maps.newLinkedHashMap();

            Iterator iterator = validateResult.iterator();

            while (iterator.hasNext()) {

                ConstraintViolation violation = (ConstraintViolation) iterator.next();

                errors.put(violation.getPropertyPath(), violation.getMessage());


            }

            return errors;
        }

    }


    public static Map<String, String> validateList(Collection<?> collection) {

        //guava提供
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;


        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }

            Object object = iterator.next();

            errors = validate(object, new Class[0]);

        } while (errors.isEmpty());

        return errors;

    }


    /**
     * 综合以上两种方式
     *
     * @param first
     * @param objects
     * @return
     */
    public static Map<String, String> validateObject(Object first, Object... objects) {

        if (objects != null && objects.length > 0) {
            validateList(Lists.asList(first, objects));

        }
        return validate(first, new Class[0]);

    }


    public static void check(Object object) throws ParamException {

        Map<String, String> map = BeanValidator.validate(object);

//        if (map != null && map.entrySet().size() > 0) {
//            throw new ParamException(map.toString());
//        }

        //使用MapUtils判断Map是否为空

        if (MapUtils.isNotEmpty(map)) {
            throw new ParamException(map.toString());
        }

    }


}



















































