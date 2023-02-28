package cn.zeroeden.parameterCheckout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParameterCheckoutApplicationTests {

    @Test
    void contextLoads() {
        String tem = "1 3";
        System.out.println(Integer.valueOf(tem));
    }

}
