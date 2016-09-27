package com.nextyu.study.webmagic;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

/**
 * created on 2016-08-01 16:38
 *
 * @author nextyu
 */
public class HtmlUnitTest {
    public static void main(String[] args) throws Exception {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = webClient.getPage("http://music.163.com/#/song?id=5041667");
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        HtmlElement body = page.getBody();
        //System.out.println(body.asXml());
        System.out.println(page.asXml());
        /*String titleText = page.getTitleText();
//        List<?> byXPath = page.getByXPath("//div[@class='p-price']");
//        System.out.println(byXPath);
        System.out.println(titleText);

        List<DomElement> elements = page.getElementsByTagName("strong");
        for (DomElement element : elements) {
            System.out.println(element.getFirstElementChild());
            System.out.println(element);
        }*/
    }
}
