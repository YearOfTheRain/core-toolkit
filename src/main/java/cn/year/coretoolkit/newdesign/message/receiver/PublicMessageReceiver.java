package cn.year.coretoolkit.newdesign.message.receiver;

import lombok.Data;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  09:59
 */
@Data
public class PublicMessageReceiver extends MessageReceiver {

    //userId，openid 填写其中一个
    /**
     * 接收者userId
     */
    private String userId;

    /**
     * 接收者openid
     */
    private String openid;
}
