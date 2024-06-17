package cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 购买成功消息推送模板
 *
 * 模板示例
 * 订单支付成功通知
 *
 * 订单号  G202205230000010
 * 商品名称 矿泉水
 * 支付方式 微信支付
 * 支付金额 200.00
 * 下单时间 2022年11月11日 12:12
 *
 * 点击查看订单
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BuySuccessTmp extends TmpBase implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 购买时间
     */
    private Date buyTime;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 支付金额
     */
    private BigDecimal paymentAmount;
    /**
     * 付款方式（零售币/积分/微信支付。。）
     */
    private String paymentMethod;

    /** 订单号 */
    private String orderNo;

    @Override
    public Map<String, String> templateParameters() {
        Map<String, String> parameter = new HashMap<>(8);
        parameter.put("character_string2", this.orderNo);
        parameter.put("thing3", this.orderNo);
        parameter.put("thing6", this.paymentMethod);
        parameter.put("amount5", this.paymentAmount.setScale(2, RoundingMode.DOWN).toPlainString());
        parameter.put("time4", SIMPLE_DATE_FORMAT.format(this.buyTime));
        return parameter;
    }

    @Override
    public String templateId() {
        return "buySuccess";
    }
}
