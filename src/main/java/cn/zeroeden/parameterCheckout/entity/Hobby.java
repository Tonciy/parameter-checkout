package cn.zeroeden.parameterCheckout.entity;

import cn.zeroeden.parameterCheckout.parameterValidationGroup.UserAdd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: Zero
 * @time: 2023/3/1
 * @description: 爱好
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hobby {

    /**
     * 名称
     */
//    @NotBlank(message = "爱好名称不能为空",groups = {UserAdd.class})
//    private  String name;

    /**
     * 花费
     */
    @NotNull(message = "花费不能为空",groups = {UserAdd.class})
    @Min(value = 0, groups = UserAdd.class, message = "花费不能为负数")
    private Integer expenditure;
}
