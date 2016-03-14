package com.tinmegali.androidmvp;

import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 14/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class TestModel {

    private MainPresenter mPresenter;
    private MainModel mModel;

    @Before
    public void setup(){
        mModel = new MainModel();
        mPresenter = mock(MainPresenter.class);
        mModel.onCreate(mPresenter);
    }

    @Test
    public void testRealModel(){
        String txt = "name";
        mModel.saveName(txt);
        assertEquals(mModel.getName(), txt);

        mModel.clearName();
        assertEquals(mModel.getName(), null);
    }
}
