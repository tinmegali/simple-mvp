package com.tinmegali.androidmvp;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;
import com.tinmegali.androidmvp.main.view.MainActivity;
import com.tinmegali.mvp.mvp.GenericModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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
public class TestPresenter {

    private MainModel mModel;
    private MainPresenter mPresenter, spyPresenter;
    private MainActivity mView;

    @Before
    @SuppressWarnings("unchecked")
    public void setup() {
        mPresenter = new MainPresenter();
        mView = mock(MainActivity.class);
        mModel = mock(MainModel.class);

        Class<MainModel> mockOpsType =
                (Class<MainModel>) mock(MainModel.class).getClass();

        mPresenter.setView(mView);
        mPresenter.onCreate(mockOpsType, mPresenter);
        spyPresenter = spy(mPresenter);
    }

    @Test
    public void testPresenter() {
        String txt = "text";
        spyPresenter.clickSaveName(txt);

        verify(spyPresenter).saveName(anyString());

        spyPresenter.clickClearName();
        verify(spyPresenter).clearName();
    }
}
