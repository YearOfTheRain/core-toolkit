package cn.year.coretoolkit.refactor.wechattemplatemessage.old.service;

import cn.year.coretoolkit.refactor.wechattemplatemessage.old.template.BeginGroupTmp;
import cn.year.coretoolkit.refactor.wechattemplatemessage.old.template.BuySuccessTmp;

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
