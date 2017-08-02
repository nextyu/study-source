package com.nextyu.study.httpclient;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient 测试.
 *
 * @author nextyu
 * @version 1.0
 */
public class HttpClientTest {

    private String requestURL = "http://mantao.lovelytao.com/saber/index/detailData?itemId=556263139450&activityId=28a9f51fe9894b478e0fb87380e8a7cb&refId=";

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

        System.out.println(entity.getContent());

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

        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");

        // 参数，就像表单提交那样
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("wp", "eyJwYWdlIjoxNSwic29ydCI6MSwiY2lkIjpudWxsLCJzZWFyY2giOiJcdTg4ZTRcdTViNTAiLCJ0eXBlIjpudWxsfQ=="));

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
        httpGet.setHeader("Host", "");
        httpGet.setHeader("Cookie", "name=value");
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
    public void testAjax() throws Exception {
        String requestURL = "http://dev.m.lailaihui.com/member/updateAccount";
        // 创建 httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet
        HttpGet httpGet = new HttpGet(requestURL);
        /*httpGet.setHeader("Host","");
        httpGet.setHeader("Cookie","name=value");*/
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
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

    /**
     * 发送xml
     * @throws Exception
     */
    @Test
    public void testSendXml() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://www.baidu.com");
        String xml = "<xml>xxxx</xml>";
        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
    }

    @Test
    public void testUrl() throws Exception {
        InputStream input = new URL("http://oss.lanlanlife.com/2b8cb4446f21b70700f4a6b42abccc13_800x800.jpg").openStream();

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "OAD7ZYR04na_PMfifO58QunEur8AYefyTIedPS5y";
        String secretKey = "aiEgTAoXtpc2i61fkjKKfIz7iSFFCHOPhrIykfJx";
        String bucket = "mall";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(input,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }

    }

}
