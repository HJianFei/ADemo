package com.apace.ydimall.base;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   : 封装视图基类
 *     version: 1.0
 * </pre>
 */

public interface BaseView {

    //显示对话框
    void displayDialog(String msg);

    //隐藏对话框
    void hideDialog();

    //显示出错信息
    void displayError(String msg);

    //显示数据为空
    void displayEmpty();
}
