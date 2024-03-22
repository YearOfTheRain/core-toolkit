package cn.year.coretoolkit.sse;

import cn.year.coretoolkit.sse.config.SseEmitterUTF8;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 图片处理工具控制器
 * @author YearOfTheRain
 * @create 2024-03-21  12:00
 */
@RestController
@RequestMapping("/api/mini/tool")
public class DealUtilController {

    /** 连接持有对象 */
    private Map<String, SseEmitterUTF8> HOLD_CONNECTION = new ConcurrentHashMap<>();

    /**
     * 图片处理方法
     * @param fileList 待处理文件
     * @return
     */
    @SneakyThrows
    @PostMapping(value = "/pictureDeal", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String pictureDeal(@RequestParam("files") List<MultipartFile> fileList) {
//        if (CollectionUtil.isEmpty(fileList)) {
//            throw new CustomBusinessException("至少上传一张待处理的图片");
//        }
//        String userId = JwtUtils.getByUserId();
//        if (HOLD_CONNECTION.containsKey(userId)) {
//            throw new CustomBusinessException("任务正在处理，请稍后再重试");
//        }
        SseEmitterUTF8 emitter = new SseEmitterUTF8(0L);

        emitter.send("请求成功");
        emitter.onCompletion(() -> HOLD_CONNECTION.remove("userId"));
        emitter.onError(throwable -> HOLD_CONNECTION.remove("userId"));
        emitter.onTimeout(() -> HOLD_CONNECTION.remove("userId"));

        return "请求成功";
    }
}
