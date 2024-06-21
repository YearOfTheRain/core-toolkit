package cn.year.coretoolkit.newdesign.message.template;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码短信发送模板
 * @author YearOfTheRain
 * @create 2024-06-21  10:06
 */
public class ValidCodeSmsTemplate extends SmsTemplateBase {

    /** 验证码 */
    private String code;
    /** 有效期 */
    private String minutes;

    /**
     * 模板创建
     * @param code 验证码
     * @param minutes 有效时间
     * @return
     */
    public static ValidCodeSmsTemplate create(String code, Long minutes) {
        ValidCodeSmsTemplate template = new ValidCodeSmsTemplate();
        template.code = code;
        template.minutes = minutes.toString();
        return template;
    }

    /**
     * 短信内容
     * {1}为您的验证码，请于{2}分钟内填写。如非本人操作，请忽略本短信。
     * @return
     */
    @Override
    public String getTemplateId() {
        return "735128";
    }

    @Override
    public Map<String, String> templateParameters() {
        Map<String, String> parameters = new HashMap<>(4);
        // 这里拼接建议按照模板要求，按顺序拼接
        parameters.put("1", code);
        parameters.put("2", minutes);
        return parameters;
    }
}
