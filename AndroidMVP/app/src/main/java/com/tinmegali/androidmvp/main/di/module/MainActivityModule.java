package com.tinmegali.androidmvp.main.di.module;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.di.scope.ActivityScope;
import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;
import com.tinmegali.androidmvp.main.view.MainActivity;

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
public class MainActivityModule {

    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity providesMainActivity() { return activity; }

    @Provides
    @ActivityScope
    MVP_MainActivity.ProvidedPresenterOps providesPresenter() {
        MainModel model = new MainModel();
        MainPresenter presenter = new MainPresenter(model);
        presenter.setView(activity);
        return presenter;
    }
}
