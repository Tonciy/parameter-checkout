package cn.zeroeden.parameterCheckout;

import cn.zeroeden.parameterCheckout.contant.City;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
class ParameterCheckoutApplicationTests {

    @Test
    void contextLoads() throws ClassNotFoundException {
//        String tem = "1 3";
//        System.out.println(Integer.valueOf(tem));
        City[] values = City.values();
        for (City value : values) {
            System.out.println(value);
        }
    }

}
