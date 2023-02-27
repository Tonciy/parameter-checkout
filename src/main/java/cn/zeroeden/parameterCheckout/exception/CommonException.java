package cn.zeroeden.parameterCheckout.exception;


import cn.zeroeden.parameterCheckout.contant.ResultCode;

/**
 * @author: Zero
 * @time: 2023/2/17
 * @description:
 */

public class CommonException extends Exception{
    public CommonException(String context) {
        super(context);
    }

    public CommonException(ResultCode context) {
        super(context.message());
    }
}
