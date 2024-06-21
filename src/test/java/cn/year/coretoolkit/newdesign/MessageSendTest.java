package cn.year.coretoolkit.newdesign;

import cn.hutool.core.lang.Assert;
import cn.year.coretoolkit.newdesign.message.MessageSendInfo;
import cn.year.coretoolkit.newdesign.message.MessageSendServiceFactory;
import cn.year.coretoolkit.newdesign.message.TemplateTypeEnum;
import cn.year.coretoolkit.newdesign.message.receiver.PublicMessageReceiver;
import cn.year.coretoolkit.newdesign.message.receiver.SmsMessageReceiver;
import cn.year.coretoolkit.newdesign.message.template.BuySuccessTmp;
import cn.year.coretoolkit.newdesign.message.template.ValidCodeSmsTemplate;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  15:46
 */
public class MessageSendTest{

    @Test
    void testSendSms() {
        MessageSendInfo messageSendInfo = new MessageSendInfo(TemplateTypeEnum.SMS,
                ValidCodeSmsTemplate.create("123456", 26L),
                Collections.singletonList(SmsMessageReceiver.create("13800000000"))
        );
        MessageSendServiceFactory.sender(messageSendInfo);
    }

    @Test
    void testSendPublic() {

        PublicMessageReceiver receiver = new PublicMessageReceiver();
        receiver.setOpenid("13800000000");
        MessageSendInfo messageSendInfo = new MessageSendInfo(TemplateTypeEnum.PUBLIC,
                BuySuccessTmp.create("123456", new Date(), "new Date()", new BigDecimal("156"), "微信支付"),
                Collections.singletonList(receiver)
        );
        MessageSendServiceFactory.sender(messageSendInfo);
    }

    @Test
    void testSendNotMatch() {

        PublicMessageReceiver receiver = new PublicMessageReceiver();
        receiver.setOpenid("13800000000");
        MessageSendInfo messageSendInfo = new MessageSendInfo(TemplateTypeEnum.PUBLIC,
                BuySuccessTmp.create("123456", new Date(), "new Date()", new BigDecimal("156"), "微信支付"),
                Collections.singletonList(SmsMessageReceiver.create("13800000000"))
        );
        try {
            MessageSendServiceFactory.sender(messageSendInfo);
        } catch (Exception e) {
            Assert.isTrue(e.getMessage().equals("有消息类型与消息接收者不匹配"));
        }
    }
}
