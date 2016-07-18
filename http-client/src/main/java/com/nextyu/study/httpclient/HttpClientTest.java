package com.nextyu.study.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient 测试.
 *
 * @author nextyu
 * @version 1.0
 */
public class HttpClientTest {

    private String requestURL = "";

    /**
     * GET请求.
     *
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        // 创建 httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet
        HttpGet httpGet = new HttpGet(requestURL);
        // 执行 get 请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 获得相应实体
        HttpEntity entity = httpResponse.getEntity();

        String result = EntityUtils.toString(entity);

        System.out.println(result);

        // 一般返回的都是 json 格式的，可以使用 alibaba fastjson 解析
        //JSONObject jsonObject = JSON.parseObject(result);
        // JSONObject 里面有个map
    }

    /**
     * POST 请求.
     *
     * @throws Exception
     */
    @Test
    public void testPostMethod() throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestURL);

        // 参数，就像表单提交那样
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("name", "value"));

        // 设置 POST 参数
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }

    /**
     * 上传图片.
     */
    @Test
    public void testUploadPic() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestURL);

        // 内容，文字，文本类型用：APPLICATION_JSON，进入源码只有它的编码是用的 UTF-8，用其他的要乱码
        StringBody value1 = new StringBody("value1", ContentType.APPLICATION_JSON);
        StringBody value2 = new StringBody("value2", ContentType.APPLICATION_JSON);

        // 图片
        File file = new File("E:\\1.jpg");
        FileBody fileBody = new FileBody(file);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        // 设置参数
        multipartEntityBuilder.addPart("name1", value1);
        multipartEntityBuilder.addPart("name2", value2);
        multipartEntityBuilder.addPart("pic", fileBody);

        httpPost.setEntity(multipartEntityBuilder.build());

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }


    /**
     * GET请求.
     *
     * @throws Exception
     */
    @Test
    public void testSetCookieMethod() throws Exception {

        String requestURL = "";
        // 创建 httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet
        HttpGet httpGet = new HttpGet(requestURL);
        httpGet.setHeader("Host","");
        httpGet.setHeader("Cookie","name=value");
        // 执行 get 请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 获得相应实体
        HttpEntity entity = httpResponse.getEntity();

        String result = EntityUtils.toString(entity);

        System.out.println(result);

        // 一般返回的都是 json 格式的，可以使用 alibaba fastjson 解析
        //JSONObject jsonObject = JSON.parseObject(result);
        // JSONObject 里面有个map
    }

    @Test
    public void testAjax() throws IOException {
        String requestURL = "http://dev.m.lailaihui.com/member/updateAccount";
        // 创建 httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet
        HttpGet httpGet = new HttpGet(requestURL);
        /*httpGet.setHeader("Host","");
        httpGet.setHeader("Cookie","name=value");*/
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");
        for (int i = 0; i < 1000; i++) {

            // 执行 get 请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            // 获得相应实体
            HttpEntity entity = httpResponse.getEntity();

            String result = EntityUtils.toString(entity);

            System.out.println(result);
        }

        // 一般返回的都是 json 格式的，可以使用 alibaba fastjson 解析
        //JSONObject jsonObject = JSON.parseObject(result);
        // JSONObject 里面有个map
    }

}
