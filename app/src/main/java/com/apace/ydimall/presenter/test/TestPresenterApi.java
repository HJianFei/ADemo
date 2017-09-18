package com.apace.ydimall.presenter.test;

import com.apace.ydimall.base.BasePresenter;
import com.apace.ydimall.view.activity.test.TestView;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface TestPresenterApi extends BasePresenter<TestView> {

    void getData();
}
