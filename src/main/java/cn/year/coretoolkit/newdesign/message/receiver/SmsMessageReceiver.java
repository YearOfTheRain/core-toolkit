package cn.year.coretoolkit.newdesign.message.receiver;

import cn.year.coretoolkit.newdesign.message.TemplateTypeEnum;
import lombok.Data;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  09:58
 */
@Data
public class SmsMessageReceiver extends MessageReceiver {

    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.SMS;
    }

    /**
     * 手机号
     */
    private String mobile;

    public static SmsMessageReceiver create(String mobile) {
        SmsMessageReceiver receiver = new SmsMessageReceiver();
        receiver.mobile = mobile;
        return receiver;
    }
}
