package com.apace.ydimall.presenter.test;

import com.apace.ydimall.model.test.TestModelApi;
import com.apace.ydimall.model.test.TestModelImpl;
import com.apace.ydimall.view.activity.test.TestView;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class TestPresenterImpl implements TestModelApi.onFinishListener, TestPresenterApi {

    private TestModelApi modelApi;
    private TestView mView;

    public TestPresenterImpl() {

        modelApi = new TestModelImpl();

    }

    @Override
    public void attachView(TestView view) {

        mView = view;

    }

    @Override
    public void detachView() {

        mView = null;

    }

    @Override
    public void onFinished(String msg) {

        mView.getDataFinished(msg);

    }

    @Override
    public void onError(String msg) {
        mView.onError(msg);

    }

    @Override
    public void getData() {

        modelApi.getData(this);

    }
}
