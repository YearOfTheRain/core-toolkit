package cn.year.coretoolkit.error;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YearOfTheRain
 * @create 2024-03-27  09:00
 */
public class ErrorUtil {

    /**
     * 发送消息
     * @param e
     * @param request
     */
    private void sendMessage(Throwable e, HttpServletRequest request) {
        String nowParams = "";
        if (request instanceof RepeatedlyRequestWrapper) {
            RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
            nowParams = HttpHelper.getBodyString(repeatedlyRequest);
        }

        // body参数为空，获取Parameter的数据
        if (StringUtils.isEmpty(nowParams)) {
            Map<String, String[]> parameterMap = request.getParameterMap();
//            if (CollectionUtil.isNotEmpty(parameterMap)) {
//                nowParams = JSON.toJSONString(parameterMap);
//            }
        }
        Map<String, String> headParams = new HashMap<>(4);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames!=null && headerNames.hasMoreElements()) {
            String name = headerNames.nextElement().toLowerCase();
            if (ErrorInfoUtil.HEADER.contains(name)) {
                headParams.put(name, request.getHeader(name));
            }
        }
//        headParams.put("remoteAddr", IpUtil.getIp());
        ErrorInfoUtil.sendErrorMessageTo(e, "零售视界前台系统",
                request.getRequestURL().toString(), nowParams, request.getMethod(), headParams);
    }
}
