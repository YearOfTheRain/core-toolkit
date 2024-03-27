package cn.year.coretoolkit.error;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 错误异常信息处理工具类
 * @author YearOfTheRain
 * @create 2023-07-07  15:09
 */
public class ErrorInfoUtil {

    private ErrorInfoUtil(){}

    public static final String ERROR_ROBOT_WEBHOOK_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=432df5c0-2e4b-42ae-8ddb-29e4f0b34a17";

    public static final Set<String> HEADER;

    static {
        HEADER = new HashSet<>(16);
        HEADER.add("host");
        HEADER.add("accesstoken");
        HEADER.add("content-type");
        HEADER.add("origin");
        HEADER.add("referer");
        HEADER.add("authorization");
        HEADER.add("platform");
        HEADER.add("user-agent");
    }
    /**
     * 发送异常信息至企微机器人
     * @param e
     * @param channel
     */
    public static void sendErrorMessageTo(Throwable e, String channel){
//        WeComMessageRobot messageRobot = SpringUtils.getBean(WeComMessageRobot.class);
//        messageRobot.setWebhook(ErrorInfoUtil.ERROR_ROBOT_WEBHOOK_URL);
//        RobotTextMessage textMessage = new RobotTextMessage();
//        textMessage.setContent(ErrorInfoUtil.getErrorInfo(e, channel + "--" + SpringUtils.activeEnvName()));
//        messageRobot.sendMessage(textMessage);
    }

    /**
     * 发送异常信息至企微机器人
     * @param e
     * @param channel
     */
    public static void sendErrorMessageTo(Throwable e, String channel, String url, String params, String method, Map<String, String> headParams){
//        WeComMessageRobot messageRobot = SpringUtils.getBean(WeComMessageRobot.class);
//        messageRobot.setWebhook(ErrorInfoUtil.ERROR_ROBOT_WEBHOOK_URL);
//        RobotTextMessage textMessage = new RobotTextMessage();
//        textMessage.setContent(ErrorInfoUtil.getErrorInfo(e, channel + "--" + SpringUtils.activeEnvName(), url, params, method, headParams));
//        messageRobot.sendMessage(textMessage);
    }

    /**
     * 组装终端异常信息
     * @param e 异常
     * @param channel 对应系统端
     * @return
     */
    private static String getErrorInfo(Throwable e, String channel) {
        return getErrorInfo(e, channel, null, null, null, null);
    }

    /**
     * 组装终端异常信息
     * @param e 异常
     * @param channel 对应系统端
     * @return
     */
    private static String getErrorInfo(Throwable e, String channel, String url, String params, String method, Map<String, String> headParams) {
//        StringBuilder message = new StringBuilder("发生时间：")
//                .append(LocalDateTimeUtil.format(LocalDateTime.now(), DateConstant.DATE_TIME_DEFAULT_FORMAT)).append("\n");
//        message.append("[ ").append(channel).append(" ]产生错误信息\n");
//        if (StrUtil.isNotBlank(url)) {
//            message.append("请求地址[ ").append(url).append(" ]\n");
//        }
//        if (StrUtil.isNotBlank(method)) {
//            message.append("请求方式[ ").append(method).append(" ]\n");
//        }
//        if (CollectionUtil.isNotEmpty(headParams)) {
//            headParams.forEach((key, value) -> message.append("请求头参数 [").append(key).append(" = ").append(value).append(" ]\n"));
//        }
//        if (StrUtil.isNotBlank(params)) {
//            message.append("请求参数[ ").append(params).append(" ]\n");
//        }
//        message.append(getStackTraceByPn(e, "com.retail"));
//        return message.toString();
        return "";
    }

    /**
     * 获取以指定包名为前缀的堆栈信息
     *
     * @param e             异常
     * @param packagePrefix 包前缀
     * @return 堆栈信息
     */
    private static String getStackTraceByPn(Throwable e, String packagePrefix) {
        StringBuilder s = new StringBuilder("\n").append(e);
        for (StackTraceElement traceElement : e.getStackTrace()) {
            if (!traceElement.getClassName().startsWith(packagePrefix)) {
                continue;
            }
            s.append("\n\tat ").append(traceElement);
        }
        return s.toString();
    }
}
