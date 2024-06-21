package cn.year.coretoolkit.newdesign.message;

/**
 * @author YearOfTheRain
 * @create 2024-06-21  11:54
 */
public class CustomerMessageSendException extends RuntimeException {

    public CustomerMessageSendException(String message) {
        super(message);
    }
}
