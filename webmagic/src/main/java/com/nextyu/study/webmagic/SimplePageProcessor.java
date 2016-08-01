package com.nextyu.study.webmagic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * created on 2016-08-01 14:20
 *
 * @author nextyu
 */
public class SimplePageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
    
    @Override
    public void process(Page page) {
        Elements imgElements = page.getHtml().getDocument().select(".p-img img");
        for (Element imgElement : imgElements) {
            String src = imgElement.attr("src");
//            page.putField("src",src);
            System.out.println(src);
        }
       /* for (String string : strings) {
            System.out.println(string);
        }*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        /*Spider.create(new SimplePageProcessor())
                .addUrl("http://www.lailaihui.com/search?keyWord=&enc=utf-8") //从"https://github.com/code4craft"开始抓
                .addPipeline(new FilePipeline("F:\\webmagic\\"))
                .thread(1) //开启5个线程抓取
                .run(); //启动爬虫*/
         Spider.create(new SimplePageProcessor())
                .addUrl("http://list.jd.com/list.html?cat=9987,653,655") //从"https://github.com/code4craft"开始抓
                .addPipeline(new FilePipeline("F:\\webmagic\\"))
                .thread(1) //开启5个线程抓取
                .run(); //启动爬虫
    }
}
