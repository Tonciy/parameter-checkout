package cn.zeroeden.parameterCheckout.parameterValidationGroup;

import javax.validation.groups.Default;

/**
 * 标志是用来做【参数检验】分组的
 */
public interface ValidGroup  {

    /**
     * 标志是用来独做 User 类的【参数检验】分组的
     */
    interface UserCurd extends ValidGroup{

        interface Add extends UserCurd{

        }
        interface Update extends UserCurd{

        }
    }
}
