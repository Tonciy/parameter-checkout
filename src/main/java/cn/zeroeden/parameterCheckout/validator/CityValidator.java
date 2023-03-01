package cn.zeroeden.parameterCheckout.validator;

import cn.zeroeden.parameterCheckout.validationAnnotationCustom.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Zero
 * @time: 2023/3/1
 * @description:
 */

/**
 * 自定义注解的检验器
 */
public class CityValidator implements ConstraintValidator<City, Integer> {
     private List<Integer> citys;

    @Override
    public void initialize(City constraintAnnotation) {
        cn.zeroeden.parameterCheckout.contant.City[] list = cn.zeroeden.parameterCheckout.contant.City.values();
        citys = Arrays.stream(list).map(i->i.getCode()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null || Objects.isNull(citys) || !citys.contains(value) ){
            return false;
        }
        return true;
    }
}
