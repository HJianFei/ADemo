package com.apace.ydimall.model.test;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   : 测试Model接口
 *     version: 1.0
 * </pre>
 */

public interface TestModelApi {

    interface onFinishListener {

        void onFinished(String msg);

        void onError(String msg);
    }

    void getData(onFinishListener listener);
}
