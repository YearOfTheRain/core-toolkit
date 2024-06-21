package cn.year.coretoolkit.newdesign.message;

/**
 * 自定义消息构建异常
 * @author YearOfTheRain
 * @create 2024-06-21  11:53
 */
public class CustomerMessageBuildException extends RuntimeException{

    public CustomerMessageBuildException(String message) {
        super(message);
    }
}
