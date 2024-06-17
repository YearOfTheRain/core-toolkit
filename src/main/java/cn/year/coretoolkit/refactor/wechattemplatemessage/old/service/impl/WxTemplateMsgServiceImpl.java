package cn.year.coretoolkit.refactor.wechattemplatemessage.old.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.year.coretoolkit.error.ErrorInfoUtil;
import cn.year.coretoolkit.refactor.wechattemplatemessage.old.service.WxTemplateMsgService;
import cn.year.coretoolkit.refactor.wechattemplatemessage.old.template.BeginGroupTmp;
import cn.year.coretoolkit.refactor.wechattemplatemessage.old.template.BuySuccessTmp;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

/**
 * 实现方法
 * @author YearOfTheRain
 * @create 2024-06-17  14:38
 */
@Service("oldWxTemplateMsgServiceImpl")
@Slf4j
public class WxTemplateMsgServiceImpl implements WxTemplateMsgService {

    @Autowired
    private WxMpService wxmpservice;

    @Override
    public boolean buySuccess(BuySuccessTmp tmp) {
        if (ifOpen()) {
            return false;
        }

        String openid;
        if (!StrUtil.isBlank(tmp.getOpenid())) {
            openid = tmp.getOpenid();
        } else {
            openid = checkOpenId(tmp.getUserId());
        }
        if (!StrUtil.isBlank(openid)) {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    // 接收者openid
                    .toUser(openid)
                    // 模板id  模板标题:支付成功通知
                    .templateId("buySuccess")
                    // 模板跳转链接
                    .url(tmp.getUrl())
                    .build();
            // 添加模板数据
            templateMessage.addData(new WxMpTemplateData("first", "您已成功下单", "#888888"))
                    .addData(new WxMpTemplateData("time4", tmp.getGoodsName(), "#AAAAAA"))
                    .addData(new WxMpTemplateData("amount5", tmp.getPaymentAmount().setScale(2, RoundingMode.DOWN).toPlainString() + "零售币", "#AAAAAA"))
                    .addData(new WxMpTemplateData("thing3", tmp.getPaymentMethod() + "人", "#AAAAAA"))
                    .addData(new WxMpTemplateData("character_string2", tmp.getBuyTime() + "小时", "#AAAAAA"))
                    .addData(new WxMpTemplateData("remark", "详情请点击这里，祝生活愉快", "#0000FF"));

            return judge(templateMessage);
        }
        return false;
    }

    @Override
    public boolean joinGroup(BeginGroupTmp tmp) {
        if (ifOpen()) {
            return false;
        }

        String openid;
        if (!StrUtil.isBlank(tmp.getOpenid())) {
            openid = tmp.getOpenid();
        } else {
            openid = checkOpenId(tmp.getUserId());
        }
        if (!StrUtil.isBlank(openid)) {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    // 接收者openid
                    .toUser(openid)
                    // 模板id  模板标题:开团成功通知
                    .templateId("begingroupSuccess")
                    // 模板跳转链接
                    .url(tmp.getUrl())
                    .build();
            // 添加模板数据
            templateMessage.addData(new WxMpTemplateData("first", "您已成功加入一个拼团", "#888888"))
                    .addData(new WxMpTemplateData("keyword1", tmp.getGoodsName(), "#AAAAAA"))
                    .addData(new WxMpTemplateData("keyword2", tmp.getGoodsPrice().setScale(2, RoundingMode.DOWN).toPlainString() + "零售币", "#AAAAAA"))
                    .addData(new WxMpTemplateData("keyword3", tmp.getGroupAmount() + "人", "#AAAAAA"))
                    .addData(new WxMpTemplateData("keyword4", tmp.getGroupTime() + "小时", "#AAAAAA"))
                    .addData(new WxMpTemplateData("remark", "详情请点击这里，祝生活愉快", "#0000FF"));

            return judge(templateMessage);
        }
        return false;
    }

    //通过userId拿openid并判断是否关注公众号
    public String checkOpenId(String UserId) {
        // 获取openId
        // 检查openId
        return "openId";
    }

    //判断动态参数的微信模板消息开关是否开启
    private boolean ifOpen() {
        // 这里可从数据库读取消息开关配置
        return false;
    }

    //发送模板消息并返回是否成功
    public boolean judge(WxMpTemplateMessage templateMessage) {
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


}
