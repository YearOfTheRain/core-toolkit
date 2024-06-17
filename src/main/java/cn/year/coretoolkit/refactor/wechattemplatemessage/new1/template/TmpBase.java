package cn.year.coretoolkit.refactor.wechattemplatemessage.new1.template;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TmpBase implements Serializable {
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
}
