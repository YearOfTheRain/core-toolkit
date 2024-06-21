package cn.year.coretoolkit.newdesign.message.template;

import cn.year.coretoolkit.newdesign.message.TemplateTypeAble;
import cn.year.coretoolkit.newdesign.message.TemplateTypeEnum;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  10:56
 */
public abstract class SmsTemplateBase extends MessageTemplate implements TemplateTypeAble {

    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.SMS;
    }
}
