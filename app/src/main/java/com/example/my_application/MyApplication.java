package com.example.my_application;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.bh.ldp.lib_base.BaseApplication;
import com.bh.ldp.lib_base.utils.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


/**
 * created by Da Peng at 2019/10/12
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        // ndroidRuntime: FATAL EXCEPTION: OkHttp Dispatcher
        //    java.lang.NoClassDefFoundError
        // Dalvik可执行文件.dex中的Java方法数引用超过65536,64k的计算方法是65536除以1024,65K的计算方法是65536除以1000,如果方法数超过64k编译器编译将无法通过.
        //
        //解决原理:分包:
        //但是现在一个大的应用,尤其是集成第三方库的,很容易超过这个数目,所以Android 5.0 之后的版本谷歌试用了名为ART的虚拟机来代替Dalvik虚拟机,ART支持从APK文件中加载多个.dex文件. 在安装期间,他会执行一个预编译操作,对.dex文件编译成一个个单一的.oat文件,在运行应用时去加载.oat文件,而不是.dex文件
        //
        // 这个错误只在(准确的说是5.0以下的机子)手机上才会出现,其他手机上不会出现.
        MultiDex.install(this);
        super.onCreate();
        LogUtils.setDebug(true);
       // initJpush();
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(30);
            }
        });
    }
//
//    //初始化极光推送
//    public void initJpush() {
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//    }

}
