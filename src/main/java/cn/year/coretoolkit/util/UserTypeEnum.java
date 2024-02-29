package cn.year.coretoolkit.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型
 * @author YearOfTheRain
 * @create 2024-02-29  15:27
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserTypeEnum implements IBaseEnum<Integer> {

    /** 枚举值 */
    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),
    ;

    /** 属性 */
    private final Integer code;
    private final String desc;
}
