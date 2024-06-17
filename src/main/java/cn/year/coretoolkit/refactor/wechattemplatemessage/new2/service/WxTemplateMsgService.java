package cn.year.coretoolkit.refactor.wechattemplatemessage.new2.service;

import cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template.TmpBase;

/**
 * 微信模板消息发送方法
 * @author YearOfTheRain
 * @create 2024-06-17  14:36
 */
public interface WxTemplateMsgService {

    /**
     * 购买成功通知
     */
    <T extends TmpBase> boolean send(T buySuccessTmp);
}
