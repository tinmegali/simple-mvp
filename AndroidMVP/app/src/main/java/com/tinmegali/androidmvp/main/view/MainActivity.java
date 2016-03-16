package com.tinmegali.androidmvp.main.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tinmegali.androidmvp.R;
import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;
import com.tinmegali.mvp.mvp.GenericMVPActivity;

/**
 * <p>
 * VIEW layer of MVP pattern.
 * </p><p>
 * Created by: Tin Megali on 25/02/16. <br/>
 * Project: AndroidMVP </br>
 * --------------------------------------------------- <br />
 * <a href="http://www.tinmegali.com">tinmegali.com</a> <br/>
 * <a href="http://www.github.com/tinmegali>github</a> <br />
 * ---------------------------------------------------
 * <p>
 * Based on
 * <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 * framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 * Dr. Douglas Schmidth</a>
 * </p>
 */
public class MainActivity
        extends
        GenericMVPActivity<MVP_MainActivity.RequiredViewOps,
                MVP_MainActivity.ProvidedPresenterOps,
                                            MainPresenter>
        implements
            MVP_MainActivity.RequiredViewOps{

    /**
     * Method that initialized MVP objects
     * {@link super#onCreate(Class, Object)} should always be called
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(MainPresenter.class,this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
