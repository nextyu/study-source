package com.nextyu.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring和HttpClient集成测试.
 *
 * @author zhouyu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class SpringHttpClientIntegrateTest {

    @Autowired
    private CloseableHttpClient httpClient;

    @Test
    public void test1() throws Exception {
        // 创建 httpGet
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        // 执行 get 请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 获得相应实体
        HttpEntity entity = httpResponse.getEntity();

        String result = EntityUtils.toString(entity);

        System.out.println(result);
    }

}
