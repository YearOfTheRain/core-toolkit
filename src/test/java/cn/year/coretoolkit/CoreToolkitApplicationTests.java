package cn.year.coretoolkit;

import cn.year.coretoolkit.util.IBaseEnum;
import cn.year.coretoolkit.util.UserTypeEnum;
import cn.year.coretoolkit.util.XmlUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

@SpringBootTest
class CoreToolkitApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertNull(null);
    }

    @Test
    void testEnum() {
        Assertions.assertTrue(UserTypeEnum.ENABLE.equals(0));

        Assertions.assertEquals(UserTypeEnum.DISABLE, IBaseEnum.getByCode(UserTypeEnum.class, 1));
    }
    @Data
    @XStreamAlias("xml")
    class WorkWechatMessageXml implements Serializable {

        private static final long serialVersionUID = -2065735016278277272L;

        @XStreamAlias("ToUserName")
        private String ToUserName;

        @XStreamAlias("FromUserName")
        private String FromUserName;

        @XStreamAlias("CreateTime")
        private String CreateTime;

        /**
         * 响应消息
         */
        @XStreamAlias("MsgType")
        private String MsgType;

        /**
         * 响应事件
         */
        @XStreamAlias("Event")
        private String Event;

        @XStreamAlias("ChangeType")
        private String ChangeType;

        @XStreamAlias("ExternalUserID")
        private String ExternalUserID;

        @XStreamAlias("Source")
        private String Source;

        @XStreamAlias("EventKey")
        private String EventKey;

        @XStreamAlias("UserID")
        private String UserID;

        @XStreamAlias("AgentID")
        private String AgentID;

        @XStreamAlias("MsgId")
        private String MsgId;

        @XStreamAlias("Content")
        private String Content;

        @XStreamAlias("ChatId")
        private String ChatId;


        @XStreamAlias("SuiteId")
        private String SuiteId;
        @XStreamAlias("AuthCorpId")
        private String AuthCorpId;
        /**
         * 客户联系回调事件
         */
        @XStreamAlias("InfoType")
        private String InfoType;
        @XStreamAlias("TimeStamp")
        private Integer TimeStamp;

        @XStreamAlias("State")
        private String State;
        @XStreamAlias("WelcomeCode")
        private String WelcomeCode;
    }

    @Test
    void testXmlUtil() {
        String str = "<xml>\n" +
                "\t<SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>\n" +
                "\t<AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>\n" +
                "\t<InfoType><![CDATA[change_external_contact]]></InfoType>\n" +
                "\t<TimeStamp>1403610513</TimeStamp>\n" +
                "\t<ChangeType><![CDATA[del_external_contact]]></ChangeType>\n" +
                "\t<UserID><![CDATA[zhangsan]]></UserID>\n" +
                "\t<ExternalUserID><![CDATA[woAJ2GCAAAXtWyujaWJHDDGi0mACH71w]]></ExternalUserID>\n" +
                "\t<Source><![CDATA[DELETE_BY_TRANSFER]]></Source>\n" +
                "</xml>";
        int iterations = 10000;

        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            XmlUtil.xmlToBean(str, WorkWechatMessageXml.class);
        }
        long endTime1 = System.currentTimeMillis();
        long duration1 = endTime1 - startTime1;
        System.out.println("Method 1 (XStream) duration: " + duration1 + " ms");
    }

}
