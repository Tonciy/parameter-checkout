package cn.zeroeden.parameterCheckout.entity;

import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zero
 * @time: 2023/3/1
 * @description:
 */

public class ValidationList<E>  implements List<E> {

    @Delegate
    @Valid
    public List<E> list = new ArrayList<>();


    @Override
    public String toString() {
        return super.toString();
    }
}
