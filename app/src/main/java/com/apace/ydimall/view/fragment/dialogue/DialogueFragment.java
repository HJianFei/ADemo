package com.apace.ydimall.view.fragment.dialogue;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apace.ydimall.R;
import com.apace.ydimall.view.fragment.dialogue.chat.ChatFragment;
import com.apace.ydimall.view.fragment.dialogue.content.ContentFragment;
import com.apace.ydimall.view.fragment.dialogue.hot.HotFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DialogueFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;
    Unbinder unbinder;
    private ChatFragment chatFragment;
    private HotFragment hotFragment;
    private ContentFragment contentFragment;


    private String mParam1;
    private String mParam2;


    public DialogueFragment() {

    }


    public static DialogueFragment newInstance(String param1, String param2) {
        DialogueFragment fragment = new DialogueFragment();
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
        View view = inflater.inflate(R.layout.fragment_dialogue, container, false);
        unbinder = ButterKnife.bind(this, view);
        //初始化页面
        vpViewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        tlTabs.setupWithViewPager(vpViewpager);
        return view;
    }

    //viewPager的适配器
    class ViewPagerAdapter extends FragmentPagerAdapter {
        int pagecount = 3;
        String[] tabbName = {"聊天", "人气", "通讯录"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (chatFragment == null) {
                    chatFragment = ChatFragment.newInstance("", "");
                }
                return chatFragment;
            } else if (position == 1) {

                if (hotFragment == null) {
                    hotFragment = HotFragment.newInstance("", "");
                }
                return hotFragment;
            } else if (position == 2) {

                if (contentFragment == null) {
                    contentFragment = ContentFragment.newInstance("", "");
                }
                return contentFragment;

            } else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return pagecount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabbName[position];
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
