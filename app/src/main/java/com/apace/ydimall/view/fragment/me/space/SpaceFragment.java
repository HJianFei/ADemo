package com.apace.ydimall.view.fragment.me.space;


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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SpaceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    private AlbumFragment albumFragment;
    private CardFragment cardFragment;
    private PersonFragment personFragment;


    public SpaceFragment() {
    }

    public static SpaceFragment newInstance(String param1, String param2) {
        SpaceFragment fragment = new SpaceFragment();
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
        View view = inflater.inflate(R.layout.fragment_space, container, false);
        unbinder = ButterKnife.bind(this, view);
        vpViewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        tlTabs.setupWithViewPager(vpViewpager);
        return view;
    }

    //viewPager的适配器
    class ViewPagerAdapter extends FragmentPagerAdapter {
        int pageCount = 3;
        String[] tabbName = {"个人中心", "TA的相册", "TA的名片"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (personFragment == null) {
                    personFragment = PersonFragment.newInstance("", "");
                }
                return personFragment;
            } else if (position == 1) {

                if (albumFragment == null) {
                    albumFragment = AlbumFragment.newInstance("", "");
                }
                return albumFragment;
            } else if (position == 2) {

                if (cardFragment == null) {
                    cardFragment = CardFragment.newInstance("", "");
                }
                return cardFragment;
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
