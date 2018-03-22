package com.example.pandatv.app;

import android.app.Application;

import com.example.pandatv.been.DaoMaster;
import com.example.pandatv.been.DaoSession;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 本丶小丝 on 2018/3/6.
 */

public class MyApp extends Application {
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }


    private static MyApp mApp;
    private DaoSession mDaoSession;

    public static MyApp getMyApp() {
        return mApp;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        UMShareAPI.get(this);
        //2debug
        Config.DEBUG = true;
        UMShareAPI.init(this, "5a9de2c38f4a9d1c3a00029f");
        mApp = this;
        createDataBase();
    }

    /**
     * 1、DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现。
     * 2、DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表。
     * 3、DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API。
     */
    private void createDataBase() {
        //创建数据库
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(mApp, "stu");
        //用数据库框架进行包装
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //提供一个Session
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
