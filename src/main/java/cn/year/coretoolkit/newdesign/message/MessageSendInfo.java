package cn.year.coretoolkit.newdesign.message;

import cn.hutool.core.collection.CollectionUtil;
import cn.year.coretoolkit.newdesign.message.receiver.MessageReceiver;
import cn.year.coretoolkit.newdesign.message.template.MessageTemplate;
import lombok.Getter;

import java.util.List;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  15:20
 */
@Getter
public class MessageSendInfo<T extends MessageTemplate, R extends MessageReceiver> {

    private final TemplateTypeEnum templateTypeEnum;

    private final T messageTemplate;

    private final List<R> messageReceivers;

    public MessageSendInfo(TemplateTypeEnum templateTypeEnum, T messageTemplate, List<R> messageReceivers) {
        this.templateTypeEnum = templateTypeEnum;
        this.messageTemplate = messageTemplate;
        this.messageReceivers = messageReceivers;
    }

    public void checkParam() {
        if (templateTypeEnum == null) {
            throw new CustomerMessageBuildException("发送消息类型不能为空");
        }
        if (messageTemplate == null) {
            throw new CustomerMessageBuildException("消息模板不能为空");
        }
        if (CollectionUtil.isEmpty(messageReceivers)) {
            throw new CustomerMessageBuildException("消息接收人不能为空");
        }

        // 检查枚举类型是否匹配
        if (!templateTypeEnum.equals(messageTemplate.getTemplateType())) {
            throw new CustomerMessageBuildException("消息类型与消息模板类型不匹配");
        }

        // 循环遍历每一个接收者是否匹配
        for (R messageReceiver : messageReceivers) {
            if (!templateTypeEnum.equals(messageReceiver.getTemplateType())) {
                throw new CustomerMessageBuildException("有消息类型与消息接收者不匹配");
            }
        }
    }

}
