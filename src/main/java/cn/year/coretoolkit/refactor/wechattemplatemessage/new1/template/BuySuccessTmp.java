package cn.year.coretoolkit.refactor.wechattemplatemessage.new1.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
}
