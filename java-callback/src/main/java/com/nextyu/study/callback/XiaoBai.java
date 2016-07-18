package com.nextyu.study.callback;

/**
 * 小白
 *
 * @author zhouyu
 */
public class XiaoBai {

    /**
     * 处理问题
     *
     * @param callBack 回调接口
     * @param question 问题
     */
    public void processQuestion(CallBack callBack, String question) {
        // 小白处理问题,模拟需要很长时间
        try {
            System.out.println("小明的问题是：" + question);
            System.out.println("小白开始处理问题......");
            // 小白：这个问题太复杂，容我想一想，想好了给你打电话
            Thread.sleep(2000);

            String result = "2";
            // 调用回调方法
            // 过了一会儿，小白打电话给小明告诉了他答案是:2
            callBack.callBackMethod(result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
