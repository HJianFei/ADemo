package com.apace.ydimall.view.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apace.ydimall.R;
import com.apace.ydimall.base.App;
import com.apace.ydimall.utils.DensityUtil;
import com.apace.ydimall.utils.LogUtil;
import com.apace.ydimall.utils.ToastUtil;
import com.apace.ydimall.view.fragment.business.BusinessFragment;
import com.apace.ydimall.view.fragment.car.CarFragment;
import com.apace.ydimall.view.fragment.dialogue.DialogueFragment;
import com.apace.ydimall.view.fragment.me.MeFragment;
import com.apace.ydimall.view.fragment.nearby.NearByFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.home_chart)
    TextView homeChart;
    @BindView(R.id.new_msg)
    TextView newMsg;
    @BindView(R.id.home_nearby)
    TextView homeNearby;
    @BindView(R.id.home_car)
    TextView homeCar;
    @BindView(R.id.home_business)
    TextView homeBusiness;
    @BindView(R.id.home_me)
    TextView homeMe;
    @BindView(R.id.btn_car_warming)
    ImageView btnCarWarming;
    @BindView(R.id.btn_car_car)
    ImageView btnCarCar;
    @BindView(R.id.btn_me_money)
    ImageView btnMeMoney;
    @BindView(R.id.btn_me_scan)
    ImageView btnMeScan;
    @BindView(R.id.btn_me_setting)
    ImageView btnMeSetting;
    @BindView(R.id.btn_global_search)
    ImageView btnGlobalSearch;
    @BindView(R.id.btn_global_menu)
    ImageView btnGlobalMenu;

    private DialogueFragment dialogueFragment;
    private NearByFragment nearByFragment;
    private CarFragment carFragment;
    private BusinessFragment businessFragment;
    private MeFragment meFragment;
    private int curIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //默认加载第一个Fragment
        setDefaultFragment();
        int i = DensityUtil.px2sp(this, 60);
        LogUtil.d("onResponse", i + ">>>");
    }

    /**
     * 默认加载第一个Fragment
     */
    private void setDefaultFragment() {
        switchFragment(0);
        setTabState(homeChart, R.drawable.ic_tab_icon_duihua_dianji, getResources().getColor(R.color.colorPrimary));
    }

    /**
     * fragment之间切换
     *
     * @param i
     */
    private void switchFragment(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (i) {
            case 0:
                if (dialogueFragment == null) {
                    dialogueFragment = DialogueFragment.newInstance("", "");
                }
                transaction.replace(R.id.container, dialogueFragment);
                curIndex = 0;
                break;
            case 1:
                if (nearByFragment == null) {
                    nearByFragment = NearByFragment.newInstance("", "");
                }
                transaction.replace(R.id.container, nearByFragment);
                curIndex = 1;
                break;
            case 2:
                if (carFragment == null) {
                    carFragment = CarFragment.newInstance("", "");
                }
                transaction.replace(R.id.container, carFragment);
                curIndex = 2;
                break;
            case 3:
                if (businessFragment == null) {
                    businessFragment = BusinessFragment.newInstance("", "");
                }
                transaction.replace(R.id.container, businessFragment);
                curIndex = 3;
                break;
            case 4:
                if (meFragment == null) {
                    meFragment = MeFragment.newInstance("", "");
                }
                transaction.replace(R.id.container, meFragment);
                curIndex = 4;
                break;
        }
        transaction.commit();
    }

    /**
     * 改变底部菜单按钮的状态
     *
     * @param textView
     * @param image
     * @param color
     */
    private void setTabState(TextView textView, int image, int color) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, image, 0, 0);
        textView.setTextColor(color);
    }

    /**
     * 重置底部按钮菜单
     */
    private void resetTabState() {
        setTabState(homeChart, R.drawable.ic_tab_icon_duihua, getResources().getColor(R.color.black_1));
        setTabState(homeNearby, R.drawable.ic_tab_icon_fujing, getResources().getColor(R.color.black_1));
        setTabState(homeCar, R.drawable.ic_tab_icon_chekuang, getResources().getColor(R.color.black_1));
        setTabState(homeBusiness, R.drawable.ic_tab_icon_chekuang, getResources().getColor(R.color.black_1));
        setTabState(homeMe, R.drawable.ic_tab_icon_wo_moren, getResources().getColor(R.color.black_1));

    }

    private void resetMenu() {

        btnCarWarming.setVisibility(View.GONE);
        btnCarCar.setVisibility(View.GONE);
        btnMeMoney.setVisibility(View.GONE);
        btnMeScan.setVisibility(View.GONE);
        btnMeSetting.setVisibility(View.GONE);
        btnGlobalSearch.setVisibility(View.GONE);
        btnGlobalMenu.setVisibility(View.GONE);

    }

    //点击事件
    @OnClick({R.id.btn_global_search, R.id.btn_global_menu, R.id.home_chart, R.id.home_nearby, R.id.home_car, R.id.home_business, R.id.home_me, R.id.btn_car_warming, R.id.btn_car_car, R.id.btn_me_money, R.id.btn_me_scan, R.id.btn_me_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_chart:
                resetTabState();
                resetMenu();
                btnGlobalMenu.setVisibility(View.VISIBLE);
                btnGlobalSearch.setVisibility(View.VISIBLE);
                setTabState(homeChart, R.drawable.ic_tab_icon_duihua_dianji, getResources().getColor(R.color.colorPrimary));
                switchFragment(0);
                break;
            case R.id.home_nearby:
                resetTabState();
                resetMenu();
                btnGlobalMenu.setVisibility(View.VISIBLE);
                btnGlobalSearch.setVisibility(View.VISIBLE);
                setTabState(homeNearby, R.drawable.ic_tab_icon_fujing_dianji, getResources().getColor(R.color.colorPrimary));
                switchFragment(1);
                break;
            case R.id.home_car:
                resetTabState();
                resetMenu();
                btnCarWarming.setVisibility(View.VISIBLE);
                btnCarCar.setVisibility(View.VISIBLE);
                btnGlobalMenu.setVisibility(View.VISIBLE);
                setTabState(homeCar, R.drawable.ic_tab_icon_chekuang_dianji, getResources().getColor(R.color.colorPrimary));
                switchFragment(2);
                break;
            case R.id.home_business:
                resetTabState();
                resetMenu();
                setTabState(homeBusiness, R.drawable.ic_tab_icon_wo_dianji, getResources().getColor(R.color.colorPrimary));
                switchFragment(3);
                break;
            case R.id.home_me:
                resetMenu();
                resetTabState();
                btnMeMoney.setVisibility(View.VISIBLE);
                btnMeScan.setVisibility(View.VISIBLE);
                btnMeSetting.setVisibility(View.VISIBLE);
                setTabState(homeMe, R.drawable.ic_tab_icon_wo_dianji, getResources().getColor(R.color.colorPrimary));
                switchFragment(4);
                break;
            case R.id.btn_global_search:
                ToastUtil.showToast(this, "搜索");
                break;
            case R.id.btn_global_menu:
                if (curIndex == 0) {
//                    CustomPopWindow customPopWindow = new CustomPopWindow(this);
//                    customPopWindow.show(btnGlobalMenu);
                } else {
                    ToastUtil.showToast(this, "全局菜单");
                }
                break;
            case R.id.btn_car_warming:
                break;
            case R.id.btn_car_car:
                break;
            case R.id.btn_me_money:
                break;
            case R.id.btn_me_scan:
                break;
            case R.id.btn_me_setting:
                break;
        }
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出云电猫", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                App.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
