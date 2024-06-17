package cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class BeginGroupTmp extends TmpBase implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 成团人数
     */
    private int groupAmount;
    /**
     * 组团时间限制 以小时为单位
     */
    private int groupTime;

    @Override
    public Map<String, String> templateParameters() {
        Map<String, String> parameter = new HashMap<>(8);
        parameter.put("first", "您已成功加入一个拼团");
        parameter.put("keyword1", this.goodsName);
        parameter.put("keyword2", this.goodsPrice.setScale(2, RoundingMode.DOWN).toPlainString() + "零售币");
        parameter.put("keyword3", this.groupAmount + "人");
        parameter.put("keyword4", this.groupTime + "小时");
        parameter.put("remark","详情请点击这里，祝生活愉快");
        return parameter;
    }

    @Override
    public String templateId() {
        return "begingroupSuccess";
    }
}
