package com.nextyu.study.webmagic;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * created on 2016-08-01 13:45
 *
 * @author nextyu
 */
public class Main1 {
    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft") //从"https://github.com/code4craft"开始抓
                .addPipeline(new JsonFilePipeline("F:\\webmagic\\"))
                .thread(1) //开启5个线程抓取
                .run(); //启动爬虫
    }
}
