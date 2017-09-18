package com.apace.ydimall.view.activity.test;

import com.apace.ydimall.base.BaseView;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface TestView extends BaseView {

    void getDataFinished(String msg);

    void onError(String errorMsg);
}
