package com.apace.ydimall.view.fragment.me;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apace.ydimall.R;
import com.apace.ydimall.view.fragment.me.affair.AffairFragment;
import com.apace.ydimall.view.fragment.me.space.SpaceFragment;
import com.apace.ydimall.widget.NoScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_viewpager)
    NoScrollViewPager vpViewpager;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;
    private AffairFragment affairFragment;
    private SpaceFragment spaceFragment;


    public MeFragment() {
    }

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        //初始化页面
        vpViewpager.setNoScroll(true);
        vpViewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        tlTabs.setupWithViewPager(vpViewpager);
        return view;
    }

    //viewPager的适配器
    class ViewPagerAdapter extends FragmentPagerAdapter {
        int pageCount = 2;
        String[] tabbName = {"我的空间", "我的事务"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (spaceFragment == null) {
                    spaceFragment = SpaceFragment.newInstance("", "");
                }
                return spaceFragment;
            } else if (position == 1) {

                if (affairFragment == null) {
                    affairFragment = AffairFragment.newInstance("", "");
                }
                return affairFragment;
            } else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return pageCount;
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
