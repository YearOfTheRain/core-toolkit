package cn.year.coretoolkit.newdesign.message;

import cn.hutool.core.util.StrUtil;
import cn.year.coretoolkit.newdesign.message.receiver.MessageReceiver;
import cn.year.coretoolkit.newdesign.message.receiver.PublicMessageReceiver;
import cn.year.coretoolkit.newdesign.message.template.MessageTemplate;
import cn.year.coretoolkit.newdesign.message.template.PublicTemplateBase;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  11:19
 */
@Slf4j
public class PublicMessageSendService implements IMessageSendService<PublicTemplateBase, PublicMessageReceiver> {

    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.PUBLIC;
    }

    @Override
    public void send(MessageSendInfo<PublicTemplateBase, PublicMessageReceiver> messageSendInfo) {
        MessageTemplate messageTemplate = messageSendInfo.getMessageTemplate();
        List<PublicMessageReceiver> messageReceivers = messageSendInfo.getMessageReceivers();

        if (messageReceivers.isEmpty()) {
            throw new CustomerMessageSendException("message receiver is empty");
        }
        if (messageTemplate instanceof TemplateTypeAble) {
            if (((TemplateTypeAble)messageTemplate).getTemplateType() != TemplateTypeEnum.PUBLIC) {
                throw new CustomerMessageBuildException("messageTemplate is not wechat public");
            }
        } else {
            throw new CustomerMessageBuildException("messageTemplate is not valid");
        }
        log.info("do send  wechat public operation");
        messageReceivers.forEach(receiver -> doSend((PublicTemplateBase)messageTemplate, receiver));
        log.info("do send success");
    }

    private String doSend(PublicTemplateBase messageTemplate, PublicMessageReceiver receiver) {
        TemplateWxMessage<PublicTemplateBase, PublicMessageReceiver> templateWxMessage = new TemplateWxMessage<PublicTemplateBase, PublicMessageReceiver>() {
            @Override
            WxMpTemplateMessage buildTemplate(PublicTemplateBase temp, PublicMessageReceiver receiver) {
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        // 接收者openid
                        .toUser(receiver.getOpenid())
                        // 模板id  模板标题:购买成功通知
                        .templateId(temp.getTemplateId())
                        // 模板跳转链接
                        .url(temp.getUrl())
                        .build();
                // 通过在对象层面组装好模板参数，简化方法
                Map<String, String> templateParameter = temp.templateParameters();
                templateParameter.forEach((key,value)-> templateMessage.addData(new WxMpTemplateData(key, value)));
                return templateMessage;
            }
        };
        return templateWxMessage.send(messageTemplate, receiver);
    }


}
/**
 * 模板消息发送核心代码
 * @param <T>
 */
@Slf4j
abstract class TemplateWxMessage<T extends PublicTemplateBase, R extends PublicMessageReceiver> {

    /**
     * 模板消息发送
     * @param temp
     * @return
     */
    public String send(T temp, R receiver) {
        if (false){
            return "false";
        }
        String openid = this.getOpenId(receiver);
        if (StrUtil.isBlank(openid)) {
            return "false";
        }
        // 设置最终发送消息的openID
        receiver.setOpenid(openid);
        log.info("最终发送消息的openID为[{}]", openid);
        WxMpTemplateMessage templateMessage = buildTemplate(temp, receiver);

//        return SpringUtils.getBean(IWxMpService.class).sendWxMpTemplate(templateMessage);
        return "true";
    }


    /**
     * 组装待发送的模板消息
     * @param temp
     * @return
     */
    abstract WxMpTemplateMessage buildTemplate(T temp, R receiver);

    /**
     * 获取 openId
     * @param receiver openId
     * @return
     */
    private String getOpenId(PublicMessageReceiver receiver) {
        if (StrUtil.isNotBlank(receiver.getOpenid())) {
            return receiver.getOpenid();
        }
        // 根据用户ID查询openID

        return null;
    }

    /**
     * 字段截取
     * @param length
     * @param message
     * @return
     */
    protected String getSub(int length, String message) {
        // 模板消息的字段显示有长度限制
        if (message.length() > length) {
            return message.substring(0, length - 1) + "*";
        }
        return message;
    }

}
