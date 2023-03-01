package cn.zeroeden.parameterCheckout.validationAnnotationCustom;

import cn.zeroeden.parameterCheckout.parameterValidationGroup.UserUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(
        validatedBy = {}
)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Documented
@NotNull()
@Length(max = 12, min = 6)
@ReportAsSingleViolation// 这个不加的话默认报的错误信息是原来几个检验注解所带有的
public @interface Id {

    String message() default "id不能为null并且长度必须位于[6-12] ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
