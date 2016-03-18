package com.tinmegali.androidmvp.main.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tinmegali.androidmvp.R;
import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.MyApp;
import com.tinmegali.androidmvp.main.di.module.MainActivityModule;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;
import com.tinmegali.mvp.mvp.StateMaintainer;

import javax.inject.Inject;

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
public class MainActivity extends AppCompatActivity

        implements
            MVP_MainActivity.RequiredViewOps{

    private final String TAG = MainActivity.class.getSimpleName();
    private final StateMaintainer mStateMaintainer = new StateMaintainer(getFragmentManager(), TAG);

    @Inject
    MVP_MainActivity.ProvidedPresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // Setup Model View Presenter
        setupMVP();

        mPresenter.clickSaveName("testing");
    }

    /**
     * Setup MVP classes.
     * Calls {@link #initialize()} or {@link #reinitialize()} that
     * create and inject the {@link MainPresenter} or recovers from <code>mStateMaintainer</code>
     */
    private void setupMVP(){
        if ( mStateMaintainer.firstTimeIn() ) {
            initialize();
        } else {
            reinitialize();
        }
    }

    /**
     * Setup the {@link MainPresenter} injection and saves in <code>mStateMaintainer</code>
     */
    private void initialize(){
        Log.d(TAG, "initialize");
        setupComponent();
        mStateMaintainer.put(MainPresenter.TAG, mPresenter);
    }

    /**
     * Recover {@link MainPresenter} from <code>mStateMaintainer</code> or creates
     * a new {@link MainPresenter} if the instance has been lost from <code>mStateMaintainer</code>
     */
    private void reinitialize() {
        Log.d(TAG, "reinitialize");
        mPresenter = mStateMaintainer.get(MainPresenter.TAG);
        if ( mPresenter == null )
            setupComponent();
    }

    /**
     * Setup the {@link com.tinmegali.androidmvp.main.di.component.MainActivityComponent}
     * to instantiate and inject a {@link MainPresenter}
     */
    private void setupComponent(){
        Log.d(TAG, "setupComponent");
        MyApp.get(this)
                .getAppComponent()
                .getMainComponentI(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public void onShowToast(String msg) {

    }

    @Override
    public void onShowToast(String msg, int duration) {

    }

    @Override
    public void onShowSnackbar(String msg, View parentView) {

    }

    @Override
    public void onShowSnackbar(String msg, View parentView, int duration) {

    }

    @Override
    public void onShowSnackbar(Snackbar snackbar) {

    }
}
