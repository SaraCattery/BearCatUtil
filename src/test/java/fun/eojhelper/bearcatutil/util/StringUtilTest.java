package fun.eojhelper.bearcatutil.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by nichenhao on 2021/06/14.
 */
@SpringBootTest
class StringUtilTest {

    @Test
    void testIsEmpty() {
        Assertions.assertEquals(true, StringUtil.isEmpty(""));
        Assertions.assertEquals(true, StringUtil.isEmpty(null));
        Assertions.assertEquals(false, StringUtil.isEmpty("bear-cat"));
    }

    @Test
    void testIsNotEmpty() {
        Assertions.assertEquals(false, StringUtil.isNotEmpty(""));
        Assertions.assertEquals(false, StringUtil.isNotEmpty(null));
        Assertions.assertEquals(true, StringUtil.isNotEmpty("bear-cat"));
    }

}
