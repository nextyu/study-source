package com.nextyu.study.webmagic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * created on 2016-08-01 15:42
 *
 * @author nextyu
 */
public class JDPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");

    @Override
    public void process(Page page) {
        Elements elements = page.getHtml().getDocument().select(".gl-item");
        for (Element element : elements) {
            String imgUrl = element.select(".p-img a img").get(0).attr("src");
            String price = element.select(".p-price .J_price i").text();
            String name = element.select(".p-name a em").text();
            System.out.println("imgUrl:"+imgUrl);
            System.out.println("price:"+price);
            System.out.println("name:"+name);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JDPageProcessor())
                .addUrl("http://list.jd.com/list.html?cat=9987,653,655") //从"https://github.com/code4craft"开始抓
                //.addPipeline(new FilePipeline("F:\\webmagic\\"))
                .thread(1) //开启5个线程抓取
                .run(); //启动爬虫
    }
}
