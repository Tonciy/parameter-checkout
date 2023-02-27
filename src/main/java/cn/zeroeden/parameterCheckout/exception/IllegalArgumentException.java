package cn.zeroeden.parameterCheckout.exception;

/**
 * @author: Zero
 * @time: 2023/2/24
 * @description:
 */

import cn.zeroeden.parameterCheckout.contant.ResultCode;

/**
 * 请求参数非法异常
 */
public class IllegalArgumentException extends Exception{
    public IllegalArgumentException(String context) {
        super(context);
    }

    public IllegalArgumentException(ResultCode context) {
        super(context.message());
    }
}
