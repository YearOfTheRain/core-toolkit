package cn.year.coretoolkit.newdesign.message;

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

}
