package com.nextyu.study.callback;

/**
 * 小明
 *
 * @author zhouyu
 */
public class XiaoMing implements CallBack {

    private XiaoBai xiaoBai;

    public XiaoMing(XiaoBai xiaoBai) {
        this.xiaoBai = xiaoBai;
    }

    /**
     * 小明打电话给小白，问：1+1=?
     *
     * @param question 问题
     */
    public void askQuestion(final String question) {

        //这里用一个线程就是异步
        new Thread(new Runnable() {
            // run 方法也是一个回调方法
            @Override
            public void run() {
                // 小明调用小白的方法，以及注册回调接口
                xiaoBai.processQuestion(XiaoMing.this, question);
            }
        }).start();

        // 小明：好的，那我出去玩了，好了给我打电话，然后就把电话挂了
        play();
    }

    // 回调方法，小白知道答案后会调用这个方法
    @Override
    public void callBackMethod(String result) {
        System.out.println("小白告诉小明的答案是：" + result);
    }

    public void play() {
        System.out.println("小明去玩了......");
    }
}
