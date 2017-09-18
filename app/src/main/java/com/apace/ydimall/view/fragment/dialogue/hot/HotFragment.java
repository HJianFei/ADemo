package com.apace.ydimall.view.fragment.dialogue.hot;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apace.ydimall.R;
import com.apace.ydimall.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HotFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.hot_social_btn)
    LinearLayout hotSocialBtn;
    @BindView(R.id.hot_group_btn)
    LinearLayout hotGroupBtn;
    Unbinder unbinder;


    private String mParam1;
    private String mParam2;

    private Activity mActivity;

    public HotFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    public static HotFragment newInstance(String param1, String param2) {
        HotFragment fragment = new HotFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.hot_social_btn, R.id.hot_group_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_social_btn:
                ToastUtil.showToast(mActivity, "社交圈");
                break;
            case R.id.hot_group_btn:
                ToastUtil.showToast(mActivity, "群聊");
                break;
        }
    }
}
