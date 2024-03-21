package cn.year.coretoolkit.redis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 分享活动提成处理消息监听器
 * @author YearOfTheRain
 * @create 2024-03-19  09:10
 */
@Component("shareActivityCommissionListener")
public class ShareActivityCommissionListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {

    }

//    public static void main(String[] args) {
//        ActivityCommissionDTO proxySerialNumberDTO = ActivityCommissionDTO.create("TG97CC", "a6757edc3b8287e9063f9057640d6572");
//
//        redisService.redisTemplate.convertAndSend(RedisTopic.SHARE_ACTIVITY_COMMISSION_TOPIC,JSONObject.toJSONString(proxySerialNumberDTO));
//    }

//    @Autowired
//    private RedisService redisService;
//    @Autowired
//    private IShareUserService shareUserService;
//    @Autowired
//    private IShareActivityService activityService;
//    @Autowired
//    private IUserCommissionRecordService userCommissionRecordService;
//
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String deserialize = (String) redisService.redisTemplate.getValueSerializer().deserialize(message.getBody());
//        ActivityCommissionDTO commissionDTO = JSONObject.parseObject(deserialize, ActivityCommissionDTO.class);
//
//        // 执行用户提成统计更新
//        String channelNo = commissionDTO.getChannelNo();
//        String userId = commissionDTO.getUserId();
//        // 查询用户提成记录表
//        CommissionTotalResponse total = userCommissionRecordService.selectCommissionTotal(channelNo, userId);
//        shareUserService.lambdaUpdate()
//                .set(ShareUser::getTotalIncomeMoney, total.getTotalIncomeMoney())
//                .set(ShareUser::getTotalCommissionMoney, total.getTotalCommissionMoney())
//                .eq(ShareUser::getActivityChannelNo, channelNo)
//                .eq(ShareUser::getUserId, userId)
//                .update();
//        // 执行活动提成统计更新
//        total = userCommissionRecordService.selectCommissionTotal(channelNo, null);
//        activityService.lambdaUpdate()
//                .set(ShareActivity::getTotalIncomeMoney, total.getTotalIncomeMoney())
//                .set(ShareActivity::getTotalCommissionMoney, total.getTotalCommissionMoney())
//                .eq(ShareActivity::getChannelNo, channelNo)
//                .update();
//    }
}
