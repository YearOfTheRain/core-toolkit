package cn.year.coretoolkit.refactor.wechattemplatemessage.old.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
}
