package cn.year.coretoolkit.newdesign.message.template;

import cn.year.coretoolkit.newdesign.message.TemplateTypeAble;

import java.util.Map;

/**
 * 消息模板
 * @author YearOfTheRain
 * @create 2024-06-20  11:25
 */
public abstract class MessageTemplate implements TemplateTypeAble {

    /**
     * 获取模板ID
     * @return
     */
    public abstract String getTemplateId();

    /**
     * 模板参数
     * @return
     */
    public abstract Map<String, String> templateParameters();
}
