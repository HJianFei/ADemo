package com.apace.ydimall.view.fragment.nearby;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apace.ydimall.R;
import com.apace.ydimall.adapter.common.CommonAdapter;
import com.apace.ydimall.adapter.common.ViewHolder;
import com.apace.ydimall.bean.NearBean;
import com.apace.ydimall.utils.ToastUtil;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class NearByFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.nearby_list_rv)
    LRecyclerView nearbyListRv;
    Unbinder unbinder;
    private Activity mActivity;


    private String mParam1;
    private String mParam2;

    private CommonAdapter<NearBean> mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<NearBean> nearBeanList = new ArrayList<>();
    private Disposable disp;


    public NearByFragment() {
    }


    public static NearByFragment newInstance(String param1, String param2) {
        NearByFragment fragment = new NearByFragment();
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
        View view = inflater.inflate(R.layout.fragment_near_by, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        NearBean nearBean;
        nearBeanList.clear();
        for (int i = 0; i < 12; i++) {
            nearBean = new NearBean();
            nearBean.setNearId(i + "");
            nearBean.setUserName("车主" + i);
            nearBean.setCarImg("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=6fe6b3f0a74bd11310c0bf603bc6cf6a/2f738bd4b31c870106f8e5652d7f9e2f0608ffe3.jpg");
            nearBean.setUserAvatar("http://7kts15.com1.z0.glb.clouddn.com/uploads/user/avatar/3570/blue.png");
            nearBean.setUserDistance(i);
            nearBean.setUserRole("管理" + i);
            nearBean.setUserId(i + "");
            nearBeanList.add(nearBean);
        }
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mAdapter = new CommonAdapter<NearBean>(mActivity, R.layout.nearby_list_item_layout, nearBeanList) {
            @Override
            public void setData(ViewHolder holder, NearBean nearBean) {
                holder.setImageWithUrl(R.id.nearby_list_item_img, nearBean.getCarImg());
                holder.setImageWithUrl(R.id.nearby_list_item_user_avatar, nearBean.getUserAvatar());
                holder.setText(R.id.nearby_list_item_user_name, nearBean.getUserName());
                holder.setText(R.id.nearby_list_user_role, nearBean.getUserRole());
                holder.setText(R.id.nearby_list_item_distance, nearBean.getUserDistance() + "Km");

            }
        };
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mActivity, mAdapter);
        nearbyListRv.setAdapter(mLRecyclerViewAdapter);
        final GridLayoutManager manager = new GridLayoutManager(mActivity, 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) nearbyListRv.getAdapter(), manager.getSpanCount()));
        nearbyListRv.setLayoutManager(manager);

        nearbyListRv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        nearbyListRv.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                ToastUtil.showToast(mActivity, nearBeanList.get(i).getUserName());
            }

            @Override
            public void onItemLongClick(View view, int i) {

            }
        });
        nearbyListRv.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        e.onNext("finished");

                    }
                }).delay(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disp = d;

                            }

                            @Override
                            public void onNext(@NonNull String s) {

                                nearBeanList.clear();
                                initData();
                                nearbyListRv.refreshComplete();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onBottom() {
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        e.onNext("finished");

                    }
                }).delay(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disp = d;
                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                NearBean nearBean;
                                for (int i = 0; i < 12; i++) {
                                    nearBean = new NearBean();
                                    nearBean.setNearId(i + "");
                                    nearBean.setUserName("新增" + i);
                                    nearBean.setCarImg("https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=6fe6b3f0a74bd11310c0bf603bc6cf6a/2f738bd4b31c870106f8e5652d7f9e2f0608ffe3.jpg");
                                    nearBean.setUserAvatar("http://7kts15.com1.z0.glb.clouddn.com/uploads/user/avatar/3570/blue.png");
                                    nearBean.setUserDistance(i);
                                    nearBean.setUserRole("管理" + i);
                                    nearBean.setUserId(i + "");
                                    nearBeanList.add(nearBean);
                                }
                                if (nearbyListRv != null) {
                                    nearbyListRv.refreshComplete();
                                }
                                mLRecyclerViewAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            @Override
            public void onScrolled(int i, int i1) {
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (disp != null) {
            disp.dispose();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
