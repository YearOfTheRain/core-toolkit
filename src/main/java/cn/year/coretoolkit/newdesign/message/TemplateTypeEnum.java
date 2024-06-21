package cn.year.coretoolkit.newdesign.message;

import cn.year.coretoolkit.util.IBaseEnum;

/**
 * 模板类型
 * @author YearOfTheRain
 * @create 2024-06-21  10:48
 */
public enum TemplateTypeEnum implements IBaseEnum<String> {

    /** 枚举值 */
    SMS("sms", "短信"),
    PUBLIC("public", "公众号"),
    EMAIL("email", "邮件"),
    ;
    private final String code;
    private final String desc;

    TemplateTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
