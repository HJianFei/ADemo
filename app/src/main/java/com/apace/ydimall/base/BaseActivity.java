package com.apace.ydimall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   : 基类Activity的封装,在BaseActivity中进行P和V的初始化绑定
 *     version: 1.0
 * </pre>
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        if (mPresenter != null) {

            mPresenter.attachView(this);
        }
        App.getInstance().addActivity(this);
    }

    /**
     * 初始化ToolBar
     *
     * @param toolbar
     * @param title
     */
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 实例化Presenter
     */
    protected abstract void createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        App.getInstance().removeActivity(this);
    }

    @Override
    public void displayDialog(String msg) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void displayError(String msg) {

    }

    @Override
    public void displayEmpty() {

    }
}
