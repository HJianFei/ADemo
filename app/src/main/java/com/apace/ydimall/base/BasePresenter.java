package com.apace.ydimall.base;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   : MVP框架的简单封装 P处理层
 *     version: 1.0
 * </pre>
 */

public interface BasePresenter<T extends BaseView> {

    //依附视图
    void attachView(T view);

    //解除视图依附
    void detachView();
}
