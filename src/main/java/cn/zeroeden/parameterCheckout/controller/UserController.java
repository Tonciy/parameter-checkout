package cn.zeroeden.parameterCheckout.controller;

import cn.zeroeden.parameterCheckout.contant.ResultCode;
import cn.zeroeden.parameterCheckout.entity.Hobby;
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
import javax.servlet.http.HttpServletRequest;
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
     * ????????????????????????
     */
    @Resource
    private Validator validator;

    @PostMapping("/add")
    public Result addUser(@RequestBody @Valid User user) {
//        if(StringUtils.isEmpty(user.getId()) ){
//            throw new IllegalArgumentException("id????????????");
//        }
//        if(StringUtils.isEmpty(user.getName())){
//            throw new IllegalArgumentException("name????????????");
//        }
//        if(Objects.isNull(user.getAge())){
//            throw new IllegalArgumentException("age????????????");
//        }
//        if(StringUtils.isEmpty(user.getEmail())){
//            throw new IllegalArgumentException("email????????????");
//        }
//        if(StringUtils.isEmpty(user.getSex())){
//            throw new IllegalArgumentException("sex????????????");
//        }
//        if(StringUtils.isEmpty(user.getPhone())){
//            throw new IllegalArgumentException("phone????????????");
//        }
//        if(user.getId().length() < 6 || user.getId().length() > 7){
//            throw new IllegalArgumentException("id????????????");
//        }
//        if( user.getAge() < 18 || user.getAge() > 65){
//            throw new IllegalArgumentException("age????????????");
//        }
//        if(!EMAIL_PATTERN.matcher(user.getEmail()).find()){
//            throw new IllegalArgumentException("email????????????");
//        }
//        if(!user.getSex().equals("M") || !user.getSex().equals("F")){
//            throw new IllegalArgumentException("sex????????????");
//        }
//        if(!PHONE_PATTERN.matcher(user.getPhone()).find()){
//            throw new IllegalArgumentException("phone????????????");
//        }
        System.out.println(user);
        log.info("????????????");
        return Result.SUCCESS();
    }

    @PostMapping("/update")
    public Result updateById(User user) {
        log.info("??????Id????????????");
        return Result.SUCCESS();
    }

    @GetMapping("/get")
    public Result get(@NotNull(message = "id????????????")
                      @Length(max = 12, min = 6, message = "id??????????????????[6-12]")
                      String id,
                      @NotBlank(message = "??????????????????")
                      String name) {
        log.info("id???{}", id);
        log.info("name:{}", name);
        return Result.SUCCESS();
    }

    @GetMapping("/get2")
    public Result get2(@Validated User user) {
        log.info("id???{}", user.toString());
        return Result.SUCCESS();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable("id") int id) {
        log.info("??????id????????????");
        return Result.SUCCESS();
    }

    /**
     * ????????????????????????????????????????????????--??????
     * @param user
     * @return
     */
    @GetMapping("/demo1")
    public Result demo1(User user){
        demoService.doTest(user);
        return Result.SUCCESS();
    }

    /**
     * ????????????????????????????????????????????????--????????????
     * @param id
     * @return
     */
    @GetMapping("/demo2")
    public Result demo2(String id){
        demoService.doTest2(id);
        return Result.SUCCESS();
    }

    /**
     * ??????????????????-1
     * @param user
     * @return
     */
    @PostMapping("/add2")
    public Result addUser2(@RequestBody User user) throws IllegalArgumentException{
        log.info("???????????????start???");
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        if (validate.isEmpty()) {
            // ????????????
            log.info("????????????");
            log.info("???????????????{}", user);
            return Result.SUCCESS();
        }else{
            log.info("????????????");
            // ??????????????????
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<User> userConstraintViolation : validate) {
                log.info(userConstraintViolation.toString());
                errorMsg.append(userConstraintViolation.getMessageTemplate()).append(";");
            }
            throw new IllegalArgumentException(errorMsg.toString());
        }
    }

    /**
     * ??????????????????-2
     * @param user
     * @return
     */
    @PostMapping("/add3")
    public Result addUser3(@RequestBody @Validated User user,BindingResult result) throws IllegalArgumentException{
        log.info("???????????????start???");
        if (result.hasErrors()) {
            log.info("????????????");
            // ??????????????????
            String errorMsg = result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            throw new IllegalArgumentException(errorMsg);
        }else{
            // ????????????
            log.info("????????????");
            log.info("???????????????{}", user);
            return Result.SUCCESS();
        }
    }

    /**
     * ??????????????????-2
     * @param user
     * @return
     */
    @PostMapping("/add33")
    public Result addUser33(BindingResult result1,@RequestBody @Validated(UserAdd.class) User user
                           ){
        log.info("???????????????start???");
        log.info(user.toString());
//        log.info(request.toString());
        log.info("result1: {}", result1.hasErrors());
//        log.info("result2: {}", result2.hasErrors());
        return Result.SUCCESS();
    }


    /**
     * ????????????-1
     * @param user
     * @return
     */
    @PostMapping("/add4")
    public Result addUser4(@RequestBody @Validated(UserAdd.class) User user) {
        log.info("????????????:{}", user);
        return Result.SUCCESS();
    }

    /**
     * ????????????-2
     * @param user
     * @return
     */
    @PostMapping("/update4")
    public Result updateById4(@RequestBody @Validated({UserUpdate.class, Default.class}) User user) {
        log.info("??????Id???????????????{}", user);
        return Result.SUCCESS();
    }

    /**
     * ????????????-3
     * @param user
     * @return
     */
    @PostMapping("/add5")
    public Result addUser5(@RequestBody @Validated(UserAdd.class) User user) {
        log.info("????????????:{}", user);
        return Result.SUCCESS();
    }

    /**
     * ????????????-4
     * @param user
     * @return
     */
    @PostMapping("/update5")
    public Result updateById5(@RequestBody @Validated({ValidGroup.UserCurd.Update.class,Default.class}) User user) {
        log.info("??????Id???????????????{}", user);
        return Result.SUCCESS();
    }


    /**
     * ????????????-1
     * @param user
     * @return
     */
    @PostMapping("/add6")
    public Result addUser6(@RequestBody @Validated(UserAdd.class) User user) {
        log.info("????????????:{}", user);
        return Result.SUCCESS();
    }


    /**
     * ????????????-1
     * @return
     */
    @PostMapping("/add7")
    public Result addUser7(@RequestBody @Validated(UserAdd.class) List<User> list) {
        log.info("????????????:{}", list);
        return Result.SUCCESS();
    }


    /**
     * ????????????-2
     * @return
     */
    @PostMapping("/add8")
    public Result addUser8(@RequestBody @Validated(UserAdd.class) ValidationList<User> list) {
        log.info("????????????:{}", list.toString());
        return Result.SUCCESS();
    }



    /**
     * ??????????????????
     * @param user
     * @return
     */
    @PostMapping("/update9")
    public Result updateById9(@RequestBody @Validated(UserUpdate.class) User user) {
        log.info("??????Id???????????????{}", user);
        return Result.SUCCESS();
    }


    /**
     * ?????????????????????
     * @return
     */
    @PostMapping("/add10")
    public Result addUser10(@RequestBody @Validated({UserAdd.class}) User user) {
        log.info("????????????:{}", user);
        return Result.SUCCESS();
    }
}
