package cn.year.coretoolkit.base.safe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 接口API需要考虑安全性
 * 如果不能实现连续扣费，保证原子性
 * 那么就需要带对应消费秘钥
 * 如果秘钥无效则不能访问
 * @author YearOfTheRain
 * @create 2024-04-03  16:22
 */
public class ApiSafe {

    /**
     * 处理前的检查，获取执行秘钥
     * @param dealNum
     * @return
     */
//    @GetMapping("/doDealCheck")
//    public Result<String> doDealCheck(@RequestParam("dealNum") Integer dealNum) {
//        // 第一步，检验
//        UserRight userRight = this.getUserRight();
//        if (Objects.isNull(userRight) || userRight.hasExpired()) {
//            throw new CustomBusinessException(201, "您的权益已过期或者已经用尽，请重新购买");
//        }
//        if (userRight.getAttachUsableNumber() >= dealNum) {
//            String secureKey = SecureUtil.md5(JwtUtils.getByUserId());
//            redisService.setCacheObject(DEAL_PICTURE_CHECK_REDIS_CACHE_KEY + secureKey, dealNum);
//            return Result.success("检验通过", secureKey);
//        }
//        throw new CustomBusinessException(201, "您的权益次数不足，请购买");
//    }

    /**
     * 图片处理方法
     * @param file 待处理文件
     * @param mattingType 处理类型 0-人像;1-通用;2-物体
     * @return
     */
//    @PostMapping("/pictureDeal")
//    public Result<Void> pictureDeal(@RequestParam("file") String file,
//                                    @RequestParam(value = "mattingType", required = false) Integer mattingType) {
//
//        if (StrUtil.isBlank(file)) {
//            throw new CustomBusinessException("请上传一张待处理的图片");
//        }
//        final int finalMattingType = Objects.isNull(mattingType) ? 1 : mattingType;
//
//        String userId = JwtUtils.getByUserId();
//        // 安全检查机制
//        String secureKey = SecureUtil.md5(userId);
//        Long decrement = redisService.decrement(DEAL_PICTURE_CHECK_REDIS_CACHE_KEY + secureKey, 1L);
//        if (decrement < 0) {
//            throw new CustomBusinessException(201, "您的权益已过期或者已经用尽，请重新购买");
//        }
//
//        UserRight userRight = this.getUserRight();
//        if (Objects.isNull(userRight) || userRight.hasExpired()) {
//            throw new CustomBusinessException(201, "您的权益已过期或者已经用尽，请重新购买");
//        }
//
//        ThreadUtil.execute(()->{
//            // 去掉base64前缀 data:image/jpeg;base64,
//            String baseFile = file.substring(file.indexOf(",", 1) + 1);
//            byte[] decodedBytes = Base64.getDecoder().decode(baseFile);
//            String filePath = "/" + System.currentTimeMillis() + ".png";
//            File outputFile = new File(filePath); // 输出文件名及格式
//
//            try (OutputStream out = new FileOutputStream(outputFile)) {
//                out.write(decodedBytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            String img;
//            // 生产环境走真实抠图处理，测试环境走假的
//            if (SpringUtils.activeProfileIsProd()) {
//                img = DealUtil.doDeal(outputFile, finalMattingType);
//            } else {
//                ThreadUtil.sleep(RandomUtil.randomLong(800L, 3950L));
//                img = SpringUtils.getBean(IDynamicParametersService.class).selectRandomHeadImg(null);
//            }
//
//            redisService.setCacheList(DEAL_TASK_PICTURE_REDIS_CACHE_KEY + userId, new DealResponse(img));
//            if (outputFile.exists()) {
//                outputFile.delete();
//            }
//        });
//        return Result.success();
//    }

    /**
     * 获取当前用户的处理详情信息
     * @return
     */
//    @GetMapping("/pictureDealInfo")
//    public Result<List<DealResponse>> pictureDealInfo() {
//        String cacheKey = DEAL_TASK_PICTURE_REDIS_CACHE_KEY + JwtUtils.getByUserId();
//        return Result.success(redisService.getCacheList(cacheKey));
//    }

    /**
     * 完成本次处理任务
     * @return
     */
//    @PostMapping("/pictureDealInfoFinish")
//    public Result<Void> pictureDealInfoFinish() {
//        String userId = JwtUtils.getByUserId();
//        List<DealResponse> listResult = redisService.getCacheList(DEAL_TASK_PICTURE_REDIS_CACHE_KEY + userId);
//        // 缓存数据清理
//        redisService.deleteObject(DEAL_TASK_PICTURE_REDIS_CACHE_KEY + userId);
//        redisService.deleteObject(DEAL_PICTURE_CHECK_REDIS_CACHE_KEY + SecureUtil.md5(userId));
//        boolean absent = redisService.setIfAbsent(DEAL_PICTURE_CHECK_REDIS_CACHE_KEY + userId + "5555", 1, 3, TimeUnit.SECONDS);
//        // 保证只有一个方法能进扣款
//        if (CollectionUtil.isNotEmpty(listResult) && absent) {
//            // 次数扣减
//            SpringUtils.publishEvent(UserRightDeductionEvent.create(userId,
//                    listResult.size(), TechProductTypeEnum.IMAGE_MATTING_MEMBER));
//        }
//        return Result.success();
//    }
}
