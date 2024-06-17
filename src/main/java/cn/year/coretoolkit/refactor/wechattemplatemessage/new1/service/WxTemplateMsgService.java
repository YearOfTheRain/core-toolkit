package cn.year.coretoolkit.refactor.wechattemplatemessage.new1.service;

import cn.year.coretoolkit.refactor.wechattemplatemessage.new1.template.BeginGroupTmp;
import cn.year.coretoolkit.refactor.wechattemplatemessage.new1.template.BuySuccessTmp;

/**
 * 微信模板消息发送方法
 * @author YearOfTheRain
 * @create 2024-06-17  14:36
 */
public interface WxTemplateMsgService {

    /**
     * 购买成功通知
     */
    boolean buySuccess(BuySuccessTmp buySuccessTmp);

    /**
     * 加入拼团成功通知
     */
    boolean joinGroup(BeginGroupTmp beginGroupTmp);
}
