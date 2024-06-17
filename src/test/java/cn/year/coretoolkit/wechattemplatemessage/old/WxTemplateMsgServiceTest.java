package cn.year.coretoolkit.wechattemplatemessage.old;
import java.math.BigDecimal;
import java.util.Date;

import cn.year.coretoolkit.refactor.wechattemplatemessage.old.service.WxTemplateMsgService;
import cn.year.coretoolkit.refactor.wechattemplatemessage.old.template.BeginGroupTmp;
import cn.year.coretoolkit.refactor.wechattemplatemessage.old.template.BuySuccessTmp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author YearOfTheRain
 * @create 2024-06-17  15:05
 */
@SpringBootTest
public class WxTemplateMsgServiceTest {

    @Autowired
    private WxTemplateMsgService oldWxTemplateMsgServiceImpl;

    @Test
    void testBuySuccess() {
        BuySuccessTmp buySuccessTmp = new BuySuccessTmp();
        buySuccessTmp.setBuyTime(new Date());
        buySuccessTmp.setGoodsName("111");
        buySuccessTmp.setPaymentAmount(new BigDecimal("0.99"));
        buySuccessTmp.setPaymentMethod("222");
        buySuccessTmp.setOrderNo("111");
        buySuccessTmp.setUserId("111");
        buySuccessTmp.setUrl("111");
        buySuccessTmp.setOpenid("1111");

        oldWxTemplateMsgServiceImpl.buySuccess(buySuccessTmp);
    }
    @Test
    void testJoinGroup() {
        BeginGroupTmp beginGroupTmp = new BeginGroupTmp();

        beginGroupTmp.setGoodsPrice(new BigDecimal("30"));
        beginGroupTmp.setGroupTime(20);
        beginGroupTmp.setGoodsName("111");
        beginGroupTmp.setGroupAmount(30);
        beginGroupTmp.setUserId("111");
        beginGroupTmp.setUrl("111");
        beginGroupTmp.setOpenid("1111");

        oldWxTemplateMsgServiceImpl.joinGroup(beginGroupTmp);
    }
}
