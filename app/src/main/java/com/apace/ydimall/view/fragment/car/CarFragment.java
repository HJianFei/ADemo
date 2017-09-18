package com.apace.ydimall.view.fragment.car;


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
import com.apace.ydimall.view.fragment.car.preserve.PreserveFragment;
import com.apace.ydimall.view.fragment.car.security.SecurityFragment;
import com.apace.ydimall.view.fragment.car.traffic.TrafficFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CarFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;
    Unbinder unbinder;


    private String mParam1;
    private String mParam2;
    private TrafficFragment trafficFragment;
    private SecurityFragment securityFragment;
    private PreserveFragment preserveFragment;


    public CarFragment() {

    }

    public static CarFragment newInstance(String param1, String param2) {
        CarFragment fragment = new CarFragment();
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
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        unbinder = ButterKnife.bind(this, view);
        //初始化页面
        vpViewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        tlTabs.setupWithViewPager(vpViewpager);
        return view;
    }

    //viewPager的适配器
    class ViewPagerAdapter extends FragmentPagerAdapter {
        int pageCount = 3;
        String[] tabbName = {"车务", "维保", "安防"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (trafficFragment == null) {
                    trafficFragment = TrafficFragment.newInstance("", "");
                }
                return trafficFragment;
            } else if (position == 1) {

                if (preserveFragment == null) {
                    preserveFragment = PreserveFragment.newInstance("", "");
                }
                return preserveFragment;
            } else if (position == 2) {

                if (securityFragment == null) {
                    securityFragment = SecurityFragment.newInstance("", "");
                }
                return securityFragment;

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
