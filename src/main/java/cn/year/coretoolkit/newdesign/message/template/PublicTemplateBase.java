package cn.year.coretoolkit.newdesign.message.template;

import cn.hutool.core.util.ObjectUtil;
import cn.year.coretoolkit.newdesign.message.TemplateTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * 公众号发送消息模板抽象类
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class PublicTemplateBase extends MessageTemplate implements Serializable {

    private static final long serialVersionUID = -1163827644019643611L;

    /**
     * 标准时间参数格式化
     */
    static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 用户点击消息跳转链接
     */
    private String url;

    public String getUrl() {
        if (ObjectUtil.isNull(url)) {
            return "";
        }
        return url;
    }

    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.PUBLIC;
    }
}
