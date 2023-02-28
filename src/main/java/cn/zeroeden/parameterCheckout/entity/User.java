package cn.zeroeden.parameterCheckout.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * @author: Zero
 * @time: 2023/2/24
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * id 长度在 6 - 12 之间
     */
    @NotNull(message = "id不能为空")
    @Length(max = 12, min = 6,message = "id长度必须位于[6-12]")
    private String id;

    /**
     * 不能为空
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年龄必须处于 18 ~ 65 这个区间
     */
    @NotNull(message = "年龄不能为空")
    @Range(max = 65, min = 18, message = "年龄取值范围必须为[18,65]")
    private Integer age;

    /**
     * 要符合邮箱格式
     */

    @NotNull(message = "邮箱地址不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 取值只能为 M,F
     */
    @NotNull(message = "性别不能为空")
    @Pattern(regexp = "^[MF]$",message = "性别只能取值M,F")
    private String sex;

    /**
     * 要符合电话号码格式
     */
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$",
    message = "手机号码格式错误")
    private String phone;
}
