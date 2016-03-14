package com.tinmegali.androidmvp;

import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;
import com.tinmegali.androidmvp.main.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * A simple Test case to illustrate how to Test Presenter
 *
 * ---------------------------------------------------
 * Created by Tin Megali on 14/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
@RunWith(JUnit4.class)
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

        mPresenter.setView(mView);
        mPresenter.testWithModel(mModel);

        spyPresenter = spy(mPresenter);
        doReturn(mModel).when(spyPresenter).getModel();
    }

    @Test
    public void testRealPresenter(){
        String txt = "text";
        doReturn(true).when(mModel).clearName();
        doReturn(true).when(mModel).saveName(anyString());

        assertEquals(mPresenter.clickSaveName(txt), true);
        assertEquals(mPresenter.clearName(), true);
    }

    @Test
    public void testSpyPresenter() {
        String txt = "text";

        spyPresenter.clickSaveName(txt);

        verify(spyPresenter).saveName(anyString());
        verify(mModel).saveName(anyString());

        spyPresenter.clickClearName();
        verify(spyPresenter).clearName();
    }
}
