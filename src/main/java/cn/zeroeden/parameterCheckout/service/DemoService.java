package cn.zeroeden.parameterCheckout.service;

import cn.zeroeden.parameterCheckout.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author: Zero
 * @time: 2023/2/28
 * @description:
 */

@Service
@Slf4j
@Validated
public class DemoService {

    public void doTest(@Valid User user){
        log.info("执行service中的方法");
    }

    public void doTest2(@NotNull(message = "id不能为空")
                        @Length(max = 12, min = 6, message = "id长度必须位于[6-12]")
                        String id){
        log.info("执行service中的方法: {}", id);
    }
}
