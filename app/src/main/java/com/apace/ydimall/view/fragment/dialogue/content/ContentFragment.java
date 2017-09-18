package com.apace.ydimall.view.fragment.dialogue.content;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apace.ydimall.R;
import com.apace.ydimall.adapter.other.ContentAdapter;
import com.apace.ydimall.bean.FriendBean;
import com.apace.ydimall.utils.LogUtil;
import com.apace.ydimall.utils.ToastUtil;
import com.apace.ydimall.widget.pinyin.CharacterParser;
import com.apace.ydimall.widget.pinyin.PinyinComparator;
import com.apace.ydimall.widget.pinyin.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContentFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidebar)
    SideBar sidebar;
    Unbinder unbinder;

    private Activity mActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private PinyinComparator pinyinComparator;
    private CharacterParser characterParser;
    private ContentAdapter adapter;

    private List<FriendBean> friendList;

    private List<FriendBean> sourceDataList = new ArrayList<>();


    public ContentFragment() {

    }


    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
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

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {

        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = PinyinComparator.getInstance();

        adapter = new ContentAdapter(mActivity, sourceDataList);

        sidebar.setTextView(dialog);
        //设置右侧触摸监听
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listview.setSelection(position);
                }

            }
        });
        View headView = LayoutInflater.from(mActivity).inflate(R.layout.item_contact_list_header, null);
        RelativeLayout new_friend = headView.findViewById(R.id.re_newfriends);
        RelativeLayout chart_room = headView.findViewById(R.id.re_chatroom);
        RelativeLayout service = headView.findViewById(R.id.re_service);
        new_friend.setOnClickListener(this);
        chart_room.setOnClickListener(this);
        service.setOnClickListener(this);
        listview.addHeaderView(headView);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(mActivity, sourceDataList.get(position - 1).username);
            }
        });

    }


    private void initData() {
        friendList = new ArrayList<>();
        FriendBean friendBean;
        for (int i = 0; i < 3; i++) {
            friendBean = new FriendBean(i + "", "好友:" + i, "http://7kts15.com1.z0.glb.clouddn.com/uploads/user/avatar/3570/blue.png");
            friendList.add(friendBean);
        }
        for (int i = 0; i < 10; i++) {
            friendBean = new FriendBean(i + "", "服务:" + i, "http://7kts15.com1.z0.glb.clouddn.com/uploads/user/avatar/3570/blue.png");
            friendList.add(friendBean);
        }
        for (int i = 0; i < 5; i++) {
            friendBean = new FriendBean(i + "", "车程:" + i, "http://7kts15.com1.z0.glb.clouddn.com/uploads/user/avatar/3570/blue.png");
            friendList.add(friendBean);
        }
        if (friendList != null && friendList.size() > 0) {

            sourceDataList = filledData(friendList); //过滤数据为有字母的字段  现在有字母 别的数据没有
        }
        //还原除了带字母字段的其他数据
        for (int k = 0; k < friendList.size(); k++) {
            sourceDataList.get(k).username = (friendList.get(k).username);
        }
        friendList = null; //释放资源

        // 根据a-z进行排序源数据
        Collections.sort(sourceDataList, pinyinComparator);
        adapter.updateListView(sourceDataList);
        for (FriendBean f : sourceDataList) {
            LogUtil.d("onResponse", f.username);

        }
    }

    /**
     * 为ListView填充数据
     *
     * @param
     * @return
     */
    private List<FriendBean> filledData(List<FriendBean> lsit) {
        List<FriendBean> mFriendList = new ArrayList<>();

        for (int i = 0; i < lsit.size(); i++) {
            FriendBean friendModel = new FriendBean();
            friendModel.username = lsit.get(i).username;
            friendModel.avatar_img = lsit.get(i).avatar_img;
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(lsit.get(i).username);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friendModel.letters = sortString.toUpperCase();
            } else {
                friendModel.letters = "#";
            }

            mFriendList.add(friendModel);
        }
        return mFriendList;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_newfriends:
                ToastUtil.showToast(mActivity, "新的朋友");
                break;
            case R.id.re_chatroom:
                ToastUtil.showToast(mActivity, "分组");
                break;
            case R.id.re_service:
                ToastUtil.showToast(mActivity, "服务商");
                break;
        }

    }
}
