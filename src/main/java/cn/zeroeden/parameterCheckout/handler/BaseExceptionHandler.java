package cn.zeroeden.parameterCheckout.handler;

import cn.zeroeden.parameterCheckout.contant.ResultCode;
import cn.zeroeden.parameterCheckout.exception.CommonException;
import cn.zeroeden.parameterCheckout.exception.IllegalArgumentException;
import cn.zeroeden.parameterCheckout.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static cn.zeroeden.parameterCheckout.contant.ResultCode.REQUEST_PARARMETER_ILLEGAL;

/**
 * @author: Zero
 * @time: 2023/2/17
 * @description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

//    /**
//     * 通用自定义异常捕获
//     *
//     * @return
//     */
//    @ExceptionHandler(value = CommonException.class)
//    public Result commonException(CommonException exception) {
//        if (exception.getMessage() != null && exception.getMessage().equals(ResultCode.REQUEST_METHOD_NOT_SUPPORT.message())) {
//            // 不支持的请求方法类型
//            return new Result(ResultCode.REQUEST_METHOD_NOT_SUPPORT);
//        }
//        if (exception.getMessage() != null) {
//            // 给定异常信息
//            return new Result(10001, exception.getMessage(), false);
//        }
//        // 请求失败
//        return new Result(ResultCode.FAIL);
//    }
//    /**
//     * 请求参数不符合异常
//     *
//     * @return
//     */
//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public Result illegalArgumentException (IllegalArgumentException e) {
//        return new Result(REQUEST_PARARMETER_ILLEGAL.code(),e.getMessage(), false);
//    }
//
//
//    @ExceptionHandler(value = BindException.class)
//    public Result parameterBindFail(BindException e){
//        return new Result(REQUEST_PARARMETER_ILLEGAL.code(),
//                e.getAllErrors().stream()
//                        .map(ObjectError::getDefaultMessage)
//                        .collect(Collectors.joining(";")),
//                false);
//    }
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public Result parameterValidationFail(ConstraintViolationException e){
//        return new Result(REQUEST_PARARMETER_ILLEGAL.code(), e.getConstraintViolations().stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.joining(";")),
//                false);
//    }
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Result methodArgumentNotValid(MethodArgumentNotValidException e){
//        return new Result(REQUEST_PARARMETER_ILLEGAL.code(),
//                e.getBindingResult().getAllErrors().stream()
//                        .map(ObjectError::getDefaultMessage)
//                        .collect(Collectors.joining(";")),
//                false);
//
//    }
//
//
//
//
//
//    /**
//     * 服务器异常统一返回
//     *
//     * @return
//     */
//    @ExceptionHandler(value = Exception.class)
//    public Result error(Exception e) {
//        return new Result(ResultCode.SERVER_ERROR);
//    }
}