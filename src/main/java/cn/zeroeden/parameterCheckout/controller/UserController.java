package cn.zeroeden.parameterCheckout.controller;

import cn.zeroeden.parameterCheckout.contant.ResultCode;
import cn.zeroeden.parameterCheckout.entity.User;
import cn.zeroeden.parameterCheckout.exception.CommonException;
import cn.zeroeden.parameterCheckout.exception.IllegalArgumentException;
import cn.zeroeden.parameterCheckout.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author: Zero
 * @time: 2023/2/24
 * @description:
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$");


    @PostMapping("/add")
    public Result addUser( @RequestBody User user) throws IllegalArgumentException {
        if(StringUtils.isEmpty(user.getId()) ){
            throw new IllegalArgumentException("id不能为空");
        }
        if(StringUtils.isEmpty(user.getName())){
            throw new IllegalArgumentException("name不能为空");
        }
        if(Objects.isNull(user.getAge())){
            throw new IllegalArgumentException("age不能为空");
        }
        if(StringUtils.isEmpty(user.getEmail())){
            throw new IllegalArgumentException("email不能为空");
        }
        if(StringUtils.isEmpty(user.getSex())){
            throw new IllegalArgumentException("sex不能为空");
        }
        if(StringUtils.isEmpty(user.getPhone())){
            throw new IllegalArgumentException("phone不能为空");
        }
        if(user.getId().length() < 6 || user.getId().length() > 7){
            throw new IllegalArgumentException("id长度非法");
        }
        if( user.getAge() < 18 || user.getAge() > 65){
            throw new IllegalArgumentException("age取值非法");
        }
        if(!EMAIL_PATTERN.matcher(user.getEmail()).find()){
            throw new IllegalArgumentException("email格式错误");
        }
        if(!user.getSex().equals("M") || !user.getSex().equals("F")){
            throw new IllegalArgumentException("sex取值非法");
        }
        if(!PHONE_PATTERN.matcher(user.getPhone()).find()){
            throw new IllegalArgumentException("phone格式错误");
        }
        log.info("增加用户");
        return Result.SUCCESS();
    }

    @GetMapping("/getById")
    public Result getById(int id){
        log.info("根据Id获取用户");
        return Result.SUCCESS();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable("id") int id){
        log.info("根据id删除用户");
        return Result.SUCCESS();
    }

}
