package cn.year.coretoolkit.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author YearOfTheRain
 * @create 2024-02-29  14:29
 */
public interface IBaseEnum<T> extends Serializable {

    /**
     * 获取code
     * @return T
     */
    T getCode();

    /**
     * 获取描述
     * @return String
     */
    String getDesc();

    /**
     * 字符串code码是否相等
     * @param code code码
     * @return
     */
    default boolean equals(String code){
        return Objects.equals(getCode(), code);
    }
    /**
     * 整数code码是否相等
     * @param code code码
     * @return
     */
    default boolean equals(Integer code){
        return Objects.equals(getCode(), code);
    }

    /**
     * 根据Code码匹配对应枚举 <br/>
     *
     * <pre class="code">
     *     UserTypeEnum userType = IBaseEnum.getByCode(UserTypeEnum.class, 200);
     * </pre>
     *
     * @param enumClass 待匹配枚举类
     * @param code   需匹配枚举的Code码
     * @return T
     * @author lu_yang
     */
    static <T, E extends IBaseEnum<T>> E getByCode(Class<E> enumClass, T code) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst()
                .orElse(null);
    }
}
