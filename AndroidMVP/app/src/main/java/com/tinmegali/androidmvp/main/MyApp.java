package com.tinmegali.androidmvp.main;

import android.app.Application;
import android.content.Context;

import com.tinmegali.androidmvp.main.di.component.AppComponent;
import com.tinmegali.androidmvp.main.di.component.DaggerAppComponent;
import com.tinmegali.androidmvp.main.di.module.AppModule;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 17/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class MyApp extends Application {


    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
