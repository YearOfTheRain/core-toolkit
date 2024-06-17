package cn.year.coretoolkit.refactor.wechattemplatemessage.new2.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.year.coretoolkit.error.ErrorInfoUtil;
import cn.year.coretoolkit.refactor.wechattemplatemessage.new2.service.WxTemplateMsgService;
import cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template.BeginGroupTmp;
import cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template.BuySuccessTmp;
import cn.year.coretoolkit.refactor.wechattemplatemessage.new2.template.TmpBase;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Map;

/**
 * 实现方法
 * @author YearOfTheRain
 * @create 2024-06-17  14:38
 */
@Service
@Slf4j
public class WxTemplateMsgServiceImpl implements WxTemplateMsgService {

    @Override
    public <T extends TmpBase> boolean send(T tmp) {
        AbstractTemplateMessageHandler<T> messageHandler = new AbstractTemplateMessageHandler<T>() {
            @Override
            WxMpTemplateMessage buildTemplate(T temp) {
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        // 接收者openid
                        .toUser(temp.getOpenid())
                        // 模板id  模板标题:支付成功通知
                        .templateId(temp.templateId())
                        // 模板跳转链接
                        .url(temp.getUrl())
                        .build();
                // 通过在对象层面组装好模板参数，简化方法
                Map<String, String> templateParameter = temp.templateParameters();
                templateParameter.forEach((key,value)-> templateMessage.addData(new WxMpTemplateData(key, value)));
                return templateMessage;
            }
        };
        return messageHandler.send(tmp);
    }

}

/**
 * 使用模板方法重构，定义执行逻辑
 * @param <T>
 */
@Slf4j
abstract class AbstractTemplateMessageHandler<T extends TmpBase> {
    @Autowired
    private WxMpService wxmpservice;

    /**
     * 模板消息发送
     * @param temp
     * @return
     */
    public boolean send(T temp) {
        if (ifOpen()){
            return false;
        }
        String openid = this.getOpenId(temp);
        if (StrUtil.isBlank(openid)) {
            return false;
        }
        // 设置最终发送消息的openID
        temp.setOpenid(openid);
        log.info("最终发送消息的openID为[{}]", openid);
        WxMpTemplateMessage templateMessage = buildTemplate(temp);

        return judge(templateMessage);
    }

    //发送模板消息并返回是否成功
    private boolean judge(WxMpTemplateMessage templateMessage) {
        log.info("执行发送模板消息方法");
        String msgId = null;
        try {
            // 发送模板消息
            msgId = wxmpservice.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("发送公众号模板消息出现异常，消息发送参数为[{}]", templateMessage, e);
            // 记录异常信息 43004 是用户未关注、未关注消息不推送，只打印日志
            if (e.getError().getErrorCode() != 43004) {
                ErrorInfoUtil.sendErrorMessageTo(e, "公众号消息");
            }
        }
        return msgId != null;
    }

    //判断动态参数的微信模板消息开关是否开启
    private boolean ifOpen() {
        // 这里可从数据库读取消息开关配置
        return false;
    }

    /**
     * 组装待发送的模板消息
     * @param temp
     * @return
     */
    abstract WxMpTemplateMessage buildTemplate(T temp);

    /**
     * 获取 openId
     * @param tmpBase openId
     * @return
     */
    private String getOpenId(TmpBase tmpBase) {
        if (StrUtil.isNotBlank(tmpBase.getOpenid())) {
            return tmpBase.getOpenid();
        }
        // 根据用户ID查询openID
        // 获取openId
        // 检查openId
        return "openId";
    }
}
