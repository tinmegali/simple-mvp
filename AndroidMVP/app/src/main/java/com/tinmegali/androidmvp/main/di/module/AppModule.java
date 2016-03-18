package com.tinmegali.androidmvp.main.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 18/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }


}
