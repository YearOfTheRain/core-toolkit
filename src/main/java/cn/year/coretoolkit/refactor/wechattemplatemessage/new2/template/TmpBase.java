package cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class TmpBase implements Serializable {
    /**
     * 标准时间参数格式化
     */
    SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final long serialVersionUID = 1L;
    //userId，openid 填写其中一个
    /**
     * 接收者userId
     */
    private String userId;
    /**
     * 用户点击消息跳转链接
     */
    private String url;
    /**
     * 接收者openid
     */
    private String openid;

    public String getUrl() {
        if (ObjectUtil.isNull(url)) {
            return "";
        }
        return url;
    }

    /**
     * 模板参数
     * @return
     */
    public abstract Map<String, String> templateParameters();

    /**
     * 模板ID
     * @return
     */
    public abstract String templateId();
}
