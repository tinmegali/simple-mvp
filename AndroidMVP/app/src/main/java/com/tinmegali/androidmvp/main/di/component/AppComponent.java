package com.tinmegali.androidmvp.main.di.component;

import android.app.Application;

import com.tinmegali.androidmvp.main.di.module.AppModule;
import com.tinmegali.androidmvp.main.di.module.MainActivityModule;
import com.tinmegali.androidmvp.main.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 18/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
@Singleton
@Component(
        modules = AppModule.class
)
public interface AppComponent {

    Application application();
    MainActivityComponent getMainComponentI(MainActivityModule module);
}
