package cn.year.coretoolkit.refactor.wechattemplatemessage;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YearOfTheRain
 * @create 2024-06-17  14:56
 */
@Configuration
public class Config {

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        return service;
    }

}
