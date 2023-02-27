package cn.zeroeden.parameterCheckout.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String id;

    /**
     * 不能为空
     */
    private String name;

    /**
     * 年龄必须处于 18 ~ 65 这个区间
     */
    private Integer age;

    /**
     * 要符合邮箱格式
     */
    private String email;

    /**
     * 取值只能为 M,F
     */
    private String sex;

    /**
     * 要符合电话号码格式
     */
    private String phone;
}
