package cn.year.coretoolkit.newdesign.message;

import cn.year.coretoolkit.newdesign.message.receiver.MessageReceiver;
import cn.year.coretoolkit.newdesign.message.template.MessageTemplate;

/**
 * 抽象的消息发送服务
 * @author YearOfTheRain
 * @create 2024-06-20  11:36
 */
public interface IMessageSendService<T extends MessageTemplate, R extends MessageReceiver> extends TemplateTypeAble {

    /**
     * 发送消息
     */
    void send(MessageSendInfo<T, R> messageSendInfo);
}
