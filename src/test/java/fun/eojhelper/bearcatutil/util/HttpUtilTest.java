package fun.eojhelper.bearcatutil.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by nichenhao on 2021/06/14.
 */
@SpringBootTest
class HttpUtilTest {

    private static final String HEARTBEAT_BASE_URL = "http://localhost:9001/heartbeat";
    private static final String HEARTBEAT_GET_URL = "/get";
    private static final String HEARTBEAT_POST_URL = "/post";

    @Test
    void testGetHttpWholeUrl() {
        Assertions.assertEquals("http://localhost:9001/heartbeat?bear=0&cat=1",
                HttpUtil.getHttpWholeUrl(HttpUtil.HTTP_SOLUTION_URL,"heartbeat", "bear=0&cat=1"));
    }

    @Test
    void testHttpGet() {
        JSONObject response = HttpUtil.httpGet(HEARTBEAT_BASE_URL + HEARTBEAT_GET_URL);
        Assertions.assertEquals(false, response.get("error"));
        Assertions.assertEquals("检测成功", response.get("message"));
    }

    @Test
    void testHttpPost() {
        JSONObject response = HttpUtil.httpPost(HEARTBEAT_BASE_URL + HEARTBEAT_POST_URL, "{}");
        Assertions.assertEquals(false, response.get("error"));
        Assertions.assertEquals("检测成功", response.get("message"));
    }

}
