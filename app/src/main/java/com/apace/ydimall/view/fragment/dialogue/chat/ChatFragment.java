package com.apace.ydimall.view.fragment.dialogue.chat;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apace.ydimall.R;
import com.apace.ydimall.adapter.common.CommonAdapter;
import com.apace.ydimall.adapter.common.ViewHolder;
import com.apace.ydimall.bean.ChatBean;
import com.apace.ydimall.utils.ToastUtil;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ChatFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.chat_list_rv)
    LRecyclerView chatListRv;
    Unbinder unbinder;
    private String mParam1;
    private String mParam2;
    private Activity mActivity;
    private List<ChatBean> chatBeanList = new ArrayList<>();
    private CommonAdapter<ChatBean> adapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;


    public ChatFragment() {
    }


    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        ChatBean chatBean;
        chatBeanList.clear();
        for (int i = 0; i < 10; i++) {
            chatBean = new ChatBean(i + "", "服务商：" + i, "http://7kts15.com1.z0.glb.clouddn.com/uploads/user/avatar/3570/blue.png", "商", "你好,我是服务商：" + i, "奥迪R8", "12:2" + i);
            chatBeanList.add(chatBean);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        adapter = new CommonAdapter<ChatBean>(mActivity, R.layout.chat_list_item_layout, chatBeanList) {

            @Override
            public void setData(ViewHolder holder, ChatBean chatBean) {
                holder.setText(R.id.chat_list_item_user_name, chatBean.getUserName());
                holder.setText(R.id.chat_list_item_unread_count, chatBean.getUnReadCount() + "");
                holder.setText(R.id.chat_list_item_time, chatBean.getTime());
                holder.setText(R.id.chat_list_item_msg, chatBean.getMsg());
                holder.setText(R.id.chat_list_item_user_car, chatBean.getUserCar());
                holder.setImageWithUrl(R.id.chat_list_item_user_avatar, chatBean.getUserAvatar());

            }
        };

        chatListRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mActivity, adapter);
        chatListRv.setAdapter(mLRecyclerViewAdapter);
        chatListRv.setPullRefreshEnabled(false);
        View head_view = LayoutInflater.from(mActivity).inflate(R.layout.chat_item_ad_layout, null);
        head_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(mActivity, "跳转进入商城");
            }
        });
        mLRecyclerViewAdapter.addHeaderView(head_view);
        mLRecyclerViewAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ToastUtil.showToast(mActivity, chatBeanList.get(i).getMsg());
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
