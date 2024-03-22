package cn.year.coretoolkit.sse.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.nio.charset.StandardCharsets;

/**
 * 响应 event-stream处理
 * @author YearOfTheRain
 * @create 2024-03-21  14:31
 */
public class SseEmitterUTF8 extends SseEmitter {

    public SseEmitterUTF8(Long timeout) {
        super(timeout);
    }

    @Override
    protected void extendResponse(ServerHttpResponse outputMessage) {
        super.extendResponse(outputMessage);
        // 中文乱码处理
        HttpHeaders headers = outputMessage.getHeaders();
        headers.setContentType(new MediaType(MediaType.TEXT_EVENT_STREAM, StandardCharsets.UTF_8));
    }
}
