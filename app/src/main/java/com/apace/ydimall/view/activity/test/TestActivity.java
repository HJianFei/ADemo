package com.apace.ydimall.view.activity.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apace.ydimall.R;
import com.apace.ydimall.base.BaseActivity;
import com.apace.ydimall.presenter.test.TestPresenterImpl;
import com.apace.ydimall.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseActivity<TestPresenterImpl> implements TestView {

    @BindView(R.id.test_get_data_btn)
    Button testGetDataBtn;
    @BindView(R.id.result_text)
    TextView resultText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void createPresenter() {

        mPresenter = new TestPresenterImpl();

    }

    @Override
    public void getDataFinished(final String msg) {
        resultText.setText(msg);
        LogUtil.d("onResponse", msg);
    }

    @Override
    public void onError(String errorMsg) {

    }

    @OnClick(R.id.test_get_data_btn)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.test_get_data_btn:
                resultText.setText("");
                mPresenter.getData();
                break;
        }
    }
}
