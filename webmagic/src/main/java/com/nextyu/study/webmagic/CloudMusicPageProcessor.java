package com.nextyu.study.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * created on 2016-08-02 10:04
 *
 * @author nextyu
 */
public class CloudMusicPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        System.out.println(html);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CloudMusicPageProcessor())
                .addUrl("http://music.163.com/song?id=5041667")
                .thread(1) //开启5个线程抓取
                .run(); //启动爬虫
    }
}
