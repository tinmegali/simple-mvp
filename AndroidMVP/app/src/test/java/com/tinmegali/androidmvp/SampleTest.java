package com.tinmegali.androidmvp;

import android.os.Build;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 13/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class SampleTest {

    private MainPresenter mPresenter;
    private MainModel mModel;


    @Before
    public void setup(){
        mPresenter = mock(MainPresenter.class, CALLS_REAL_METHODS);
        mModel = mock(MainModel.class, CALLS_REAL_METHODS);

        when(mModel.getPresenter()).thenReturn(mPresenter);
        when(mPresenter.getModel()).thenReturn(mModel);
    }


    @Test
    public void simpleTest(){
        String name = "simple_mvp";

        // Add name
        MVP_MainActivity.ProvidedPresenterOps presenterOps = mPresenter;
        presenterOps.clickSaveName(name);
        verify(mPresenter.getModel(), atLeastOnce()).saveName(anyString());
        verify(mPresenter).onNameSaved(name);

        // Clear name
        presenterOps.clickClearName();
        verify(mPresenter.getModel(), atLeastOnce()).clearName();
        verify(mPresenter).onNameCleared();

    }


}