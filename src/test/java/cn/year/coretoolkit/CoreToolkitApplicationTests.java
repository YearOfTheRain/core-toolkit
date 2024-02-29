package cn.year.coretoolkit;

import cn.year.coretoolkit.util.UserTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreToolkitApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertNull(null);
    }

    @Test
    void testEnum() {
        Assertions.assertTrue(UserTypeEnum.ENABLE.equals(0));
    }

}
