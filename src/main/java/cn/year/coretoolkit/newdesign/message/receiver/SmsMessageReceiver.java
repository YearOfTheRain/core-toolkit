package cn.year.coretoolkit.newdesign.message.receiver;

import lombok.Data;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  09:58
 */
@Data
public class SmsMessageReceiver extends MessageReceiver {

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
