package cn.year.coretoolkit.newdesign.message;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  15:42
 */
public class MessageSendServiceFactory {

    public static void sender(MessageSendInfo messageSendInfo) {
        IMessageSendService messageSendService;
        switch (messageSendInfo.getTemplateTypeEnum()) {
//            case EMAIL:
//                return new EmailMessageSendService();
            case SMS:
                messageSendService = new SmsMessageSendServiceImpl();
                break;
            case PUBLIC:
                messageSendService = new PublicMessageSendServiceImpl();
                break;
            default:
                throw new CustomerMessageSendException("不支持的消息类型");
        }
        messageSendService.send(messageSendInfo);
    }
}
