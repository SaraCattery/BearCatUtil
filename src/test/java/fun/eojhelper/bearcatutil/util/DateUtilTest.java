package fun.eojhelper.bearcatutil.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Created by nichenhao on 2021/06/20.
 */
@SpringBootTest
class DateUtilTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilTest.class);

    @Test
    void testConvert() {
        String dateStr = "1926/08/17 00:00:00";
        Date date = new Date();
        Assertions.assertEquals(dateStr, DateUtil.convertStrFromDate(DateUtil.convertDateFromStr(dateStr)));
        Assertions.assertEquals(date.getTime()/1000*1000, DateUtil.convertDateFromStr(DateUtil.convertStrFromDate(date)).getTime());
    }

}
