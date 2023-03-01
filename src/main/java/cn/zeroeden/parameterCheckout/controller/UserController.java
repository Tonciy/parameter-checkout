package cn.zeroeden.parameterCheckout.controller;

import cn.zeroeden.parameterCheckout.contant.ResultCode;
import cn.zeroeden.parameterCheckout.entity.User;
import cn.zeroeden.parameterCheckout.entity.ValidationList;
import cn.zeroeden.parameterCheckout.exception.CommonException;
import cn.zeroeden.parameterCheckout.exception.IllegalArgumentException;
import cn.zeroeden.parameterCheckout.model.Result;
import cn.zeroeden.parameterCheckout.parameterValidationGroup.UserAdd;
import cn.zeroeden.parameterCheckout.parameterValidationGroup.UserUpdate;
import cn.zeroeden.parameterCheckout.parameterValidationGroup.ValidGroup;
import cn.zeroeden.parameterCheckout.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: Zero
 * @time: 2023/2/24
 * @description:
 */

@Slf4j
@RestController
@RequestMapping("/user")
//@Validated
public class UserController {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$");

    @Resource
    private DemoService demoService;

    /**
     * 用来测试手动校验
     */
    @Resource
    private Validator validator;

    @PostMapping("/add")
    public Result addUser(@RequestBody @Valid User user) {
//        if(StringUtils.isEmpty(user.getId()) ){
//            throw new IllegalArgumentException("id不能为空");
//        }
//        if(StringUtils.isEmpty(user.getName())){
//            throw new IllegalArgumentException("name不能为空");
//        }
//        if(Objects.isNull(user.getAge())){
//            throw new IllegalArgumentException("age不能为空");
//        }
//        if(StringUtils.isEmpty(user.getEmail())){
//            throw new IllegalArgumentException("email不能为空");
//        }
//        if(StringUtils.isEmpty(user.getSex())){
//            throw new IllegalArgumentException("sex不能为空");
//        }
//        if(StringUtils.isEmpty(user.getPhone())){
//            throw new IllegalArgumentException("phone不能为空");
//        }
//        if(user.getId().length() < 6 || user.getId().length() > 7){
//            throw new IllegalArgumentException("id长度非法");
//        }
//        if( user.getAge() < 18 || user.getAge() > 65){
//            throw new IllegalArgumentException("age取值非法");
//        }
//        if(!EMAIL_PATTERN.matcher(user.getEmail()).find()){
//            throw new IllegalArgumentException("email格式错误");
//        }
//        if(!user.getSex().equals("M") || !user.getSex().equals("F")){
//            throw new IllegalArgumentException("sex取值非法");
//        }
//        if(!PHONE_PATTERN.matcher(user.getPhone()).find()){
//            throw new IllegalArgumentException("phone格式错误");
//        }
        System.out.println(user);
        log.info("增加用户");
        return Result.SUCCESS();
    }

    @PostMapping("/update")
    public Result updateById(User user) {
        log.info("根据Id修改用户");
        return Result.SUCCESS();
    }

    @GetMapping("/get")
    public Result get(@NotNull(message = "id不能为空")
                      @Length(max = 12, min = 6, message = "id长度必须位于[6-12]")
                      String id,
                      @NotBlank(message = "姓名不能为空")
                      String name) {
        log.info("id：{}", id);
        log.info("name:{}", name);
        return Result.SUCCESS();
    }

    @GetMapping("/get2")
    public Result get2(@Validated User user) {
        log.info("id：{}", user.toString());
        return Result.SUCCESS();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable("id") int id) {
        log.info("根据id删除用户");
        return Result.SUCCESS();
    }

    /**
     * 在其他组件层面上进行【参数校验】--对象
     * @param user
     * @return
     */
    @GetMapping("/demo1")
    public Result demo1(User user){
        demoService.doTest(user);
        return Result.SUCCESS();
    }

    /**
     * 在其他组件层面上进行【参数校验】--单个参数
     * @param id
     * @return
     */
    @GetMapping("/demo2")
    public Result demo2(String id){
        demoService.doTest2(id);
        return Result.SUCCESS();
    }

    /**
     * 手动校验测试-1
     * @param user
     * @return
     */
    @PostMapping("/add2")
    public Result addUser2(@RequestBody User user) throws IllegalArgumentException{
        log.info("手动校验【start】");
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        if (validate.isEmpty()) {
            // 检验通过
            log.info("校验通过");
            log.info("增加用户：{}", user);
            return Result.SUCCESS();
        }else{
            log.info("校验失败");
            // 用来拼接信息
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<User> userConstraintViolation : validate) {
                log.info(userConstraintViolation.toString());
                errorMsg.append(userConstraintViolation.getMessageTemplate()).append(";");
            }
            throw new IllegalArgumentException(errorMsg.toString());
        }
    }

    /**
     * 手动校验测试-2
     * @param user
     * @return
     */
    @PostMapping("/add3")
    public Result addUser3(@RequestBody @Validated User user,BindingResult result) throws IllegalArgumentException{
        log.info("手动校验【start】");
        if (result.hasErrors()) {
            log.info("校验失败");
            // 用来拼接信息
            String errorMsg = result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new IllegalArgumentException(errorMsg);
        }else{
            // 检验通过
            log.info("校验通过");
            log.info("增加用户：{}", user);
            return Result.SUCCESS();
        }
    }


    /**
     * 分组校验-1
     * @param user
     * @return
     */
    @PostMapping("/add4")
    public Result addUser4(@RequestBody @Validated(UserAdd.class) User user) {
        log.info("增加用户:{}", user);
        return Result.SUCCESS();
    }

    /**
     * 分组校验-2
     * @param user
     * @return
     */
    @PostMapping("/update4")
    public Result updateById4(@RequestBody @Validated({UserUpdate.class, Default.class}) User user) {
        log.info("根据Id修改用户：{}", user);
        return Result.SUCCESS();
    }

    /**
     * 分组校验-3
     * @param user
     * @return
     */
    @PostMapping("/add5")
    public Result addUser5(@RequestBody @Validated(UserAdd.class) User user) {
        log.info("增加用户:{}", user);
        return Result.SUCCESS();
    }

    /**
     * 分组校验-4
     * @param user
     * @return
     */
    @PostMapping("/update5")
    public Result updateById5(@RequestBody @Validated({ValidGroup.UserCurd.Update.class,Default.class}) User user) {
        log.info("根据Id修改用户：{}", user);
        return Result.SUCCESS();
    }


    /**
     * 嵌套查询-1
     * @param user
     * @return
     */
    @PostMapping("/add6")
    public Result addUser6(@RequestBody @Validated(UserAdd.class) User user) {
        log.info("增加用户:{}", user);
        return Result.SUCCESS();
    }


    /**
     * 集合检验-1
     * @return
     */
    @PostMapping("/add7")
    public Result addUser7(@RequestBody @Validated(UserAdd.class) List<User> list) {
        log.info("增加用户:{}", list);
        return Result.SUCCESS();
    }


    /**
     * 集合检验-2
     * @return
     */
    @PostMapping("/add8")
    public Result addUser8(@RequestBody @Validated(UserAdd.class) ValidationList<User> list) {
        log.info("增加用户:{}", list.toString());
        return Result.SUCCESS();
    }



    /**
     * 组合检验注解
     * @param user
     * @return
     */
    @PostMapping("/update9")
    public Result updateById9(@RequestBody @Validated(UserUpdate.class) User user) {
        log.info("根据Id修改用户：{}", user);
        return Result.SUCCESS();
    }

}
