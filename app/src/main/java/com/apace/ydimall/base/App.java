package com.apace.ydimall.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.apace.ydimall.utils.CacheUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     author : HJianFei
 *     e-mail : 190766172@qq.com
 *     time   : 2017-08-09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class App extends Application {

    private static App instance;
    private Set<Activity> allActivities;
    private CacheUtils mCache;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mCache = CacheUtils.get(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //同步
    public static synchronized App getInstance() {
        return instance;
    }

    public CacheUtils getmCache() {
        if (mCache == null) {
            mCache = CacheUtils.get(this);
        }
        return mCache;
    }

    /**
     * 添加activity
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出app
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
