package com.nextyu.study.callback;

import org.junit.Test;

/**
 * @author zhouyu
 */
public class CallBackTest {

    @Test
    public void test() throws InterruptedException {
        XiaoBai xiaoBai = new XiaoBai();
        XiaoMing xiaoMing = new XiaoMing(xiaoBai);

        String question = "1+1=?";
        xiaoMing.askQuestion(question);

        // 主线程休眠10秒钟，因为调用小白解决问题的方法是new的一个子线程，
        // 子线程休眠2秒钟来模拟小白解决问题的时间，如果主线程不休眠10秒钟
        // 直接执行下去，子线程还没来得急执行，主线程就已经执行完了，子线程也跟着死掉了
        // 不会再调用回调接口的方法
        Thread.sleep(10000);
    }
}
