package cn.year.coretoolkit.newdesign.message;

import cn.hutool.core.collection.CollectionUtil;
import cn.year.coretoolkit.newdesign.message.receiver.SmsMessageReceiver;
import cn.year.coretoolkit.newdesign.message.template.MessageTemplate;
import cn.year.coretoolkit.newdesign.message.template.SmsTemplateBase;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  11:19
 */
@Slf4j
public class SmsMessageSendService implements IMessageSendService<SmsTemplateBase, SmsMessageReceiver> {

    private SendSmsResponse res;
    private final HashMap<String, String> result = new HashMap<>();


    @Override
    public TemplateTypeEnum getTemplateType() {
        return TemplateTypeEnum.SMS;
    }

    @Override
    public void send(MessageSendInfo<SmsTemplateBase, SmsMessageReceiver> messageSendInfo) {
        messageSendInfo.checkParam();
        SmsTemplateBase messageTemplate = messageSendInfo.getMessageTemplate();
        List<SmsMessageReceiver> messageReceivers = messageSendInfo.getMessageReceivers();
        if (CollectionUtil.isEmpty(messageReceivers)) {
            throw new CustomerMessageBuildException("messageReceivers is empty");
        }

        if (Objects.isNull(messageTemplate)) {
            throw new CustomerMessageBuildException("messageTemplate is null");
        }
        if (messageTemplate.getTemplateType() != TemplateTypeEnum.SMS) {
            throw new CustomerMessageBuildException("messageTemplate is not sms");
        }
        log.info("do send sms operation");

        Map<String, String> stringStringMap = doSend(messageReceivers.stream().map(SmsMessageReceiver::getMobile).toArray(String[]::new), messageTemplate.getTemplateId(), "",
                messageTemplate.templateParameters().values().toArray(new String[]{}));
        log.info("do send success");
    }

    /**
     * @param phoneNumberSet   要发送的用户手机号 可以为数组
     * @param templateId       要使用的模板的id
     * @param sessionContext   用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回
     * @param templateParamSet 模板参数: 若无模板参数，则设置为空
     * @return 消息接口json
     */
    private Map<String, String> doSend(String[] phoneNumberSet, String templateId, String sessionContext, String... templateParamSet) {
        if (true) {
            return result;
        }
        final String SECRET_ID = "secret_id";
        final String SECRET_KEY = "secret_key";
        final String APP_ID = "app_id";
        final String signName = "sign_name";
        try {
            //实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            httpProfile.setReqMethod("POST");
            /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
             * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");


            /* 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();

            clientProfile.setHttpProfile(httpProfile);

            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();

            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
            req.setSmsSdkAppId(APP_ID);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
            req.setSignName(signName);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            req.setSessionContext(sessionContext);

            /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
            req.setTemplateId(templateId);

            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
            req.setPhoneNumberSet(phoneNumberSet);

            /* 模板参数: 若无模板参数，则设置为空 */
            req.setTemplateParamSet(templateParamSet);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            res = client.SendSms(req);
            res.toMap(result, "");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            res.toMap(result, "");
            return result;
        }

    }

}
