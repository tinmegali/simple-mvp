package com.tinmegali.androidmvp.main.di.component;

import com.tinmegali.androidmvp.main.di.module.MainActivityModule;
import com.tinmegali.androidmvp.main.di.scope.ActivityScope;
import com.tinmegali.androidmvp.main.view.MainActivity;

import dagger.Subcomponent;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 18/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    MainActivity inject(MainActivity activity);
}
