package fun.eojhelper.bearcatutil.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by nichenhao on 2021/06/14.
 */
public class HttpUtil {

    public static final String HTTP_SOLUTION_URL = "http://localhost:9001/";
    public static final String HTTP_CACHE_URL = "http://localhost:9002/";

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static final Integer CONN_TIMEOUT = 15000;
    private static final Integer SO_TIMEOUT = 60000;
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String UTF8_CHARSET = "UTF-8";

    public static String getHttpWholeUrl(String baseUrl, String extraUrl, String paramUrl) {
        return String.format("%s%s?%s", baseUrl, extraUrl, paramUrl);
    }

    public static JSONObject httpGet(String url) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT);
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, SO_TIMEOUT);
        getMethod.addRequestHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
        JSONObject response = new JSONObject();
        try {
            httpClient.executeMethod(getMethod);
            InputStream inputStream = getMethod.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            getMethod.releaseConnection();
            String result = buffer.toString();
            JSONObject resultJson = JSONObject.parseObject(result);
            response.put("error", !resultJson.get("code").equals(200));
            response.put("data", resultJson.get("data"));
            response.put("message", resultJson.get("message"));
            return response;
        } catch (IOException e) {
            LOGGER.error("HttpGet请求失败, url:{}, e:{}", url, e.getCause());
            response.put("error", true);
            response.put("data", "");
            response.put("message", "未知错误，请稍后重试");
            return response;
        }
    }

    public static JSONObject httpPost(String url, String paramJson) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT);
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, SO_TIMEOUT);
        postMethod.addRequestHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
        JSONObject response = new JSONObject();
        try {
            RequestEntity entity = new StringRequestEntity(paramJson, CONTENT_TYPE_VALUE, UTF8_CHARSET);
            postMethod.setRequestEntity(entity);
            httpClient.executeMethod(postMethod);
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            postMethod.releaseConnection();
            String result = buffer.toString();
            JSONObject resultJson = JSONObject.parseObject(result);
            response.put("error", !resultJson.get("code").equals(200));
            response.put("data", resultJson.get("data"));
            response.put("message", resultJson.get("message"));
            return response;
        } catch (IOException e) {
            LOGGER.error("HttpPost请求失败, url:{}, paramJson:{}, e:{}", url, paramJson, e.getCause());
            response.put("error", true);
            response.put("data", "");
            response.put("message", "未知错误，请稍后重试");
            return response;
        }
    }

}
