package com.nextyu.study.webmagic;

import com.gargoylesoftware.htmlunit.BrowserVersion;
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
        HtmlPage page = webClient.getPage("https://list.tmall.com/search_product.htm?spm=a221y.601495ac9f02c0ce1fb61e4f80f23a71.0.0.URyRHh&cat=50928001&acm=lb-zebra-164294-973296.1003.8.842708&scm=1003.8.lb-zebra-164294-973296.ITEM_14629133791092_842708");
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
