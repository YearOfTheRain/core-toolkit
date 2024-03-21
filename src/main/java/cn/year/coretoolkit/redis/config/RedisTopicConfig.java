package cn.year.coretoolkit.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 初始化监听
 * @author YearOfTheRain
 * @create 2024-03-18  19:04
 */
@Configuration
public class RedisTopicConfig {

    @Autowired
    private MessageListener shareActivityCommissionListener;

    /**
     * 初始化一个Redis消息监听容器
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 添加其他配置，如线程池大小等
//        container.addMessageListener(shareActivityCommissionListener, new ChannelTopic(RedisTopic.SHARE_ACTIVITY_COMMISSION_TOPIC));
        return container;
    }
}