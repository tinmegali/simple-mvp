package com.tinmegali.mvp.mvp;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 25/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com"</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 * framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 * Dr. Douglas Schmidth</a>
 * ---------------------------------------------------
 */

import android.util.Log;

/**
 * Abstract class of MODEL layer on MVP pattern.
 * Should be extended by any MODEL object.
 * @param <RequiredPresenterOps>    Interface with method given to MODEL
 *                                  to access the Presenter. Should act
 *                                  as a callback to data operations
 */

public abstract class GenericModel<RequiredPresenterOps> implements ModelOps<RequiredPresenterOps>  {

    private String TAG = getClass().getSimpleName();

    // PRESENTER operations available to MODEL
    private RequiredPresenterOps mPresenter;

    /**
     * Initialize object. Called by {@link GenericPresenter#initialize(Class, Object)}
     * Saves the PRESENTER reference.
     *
     * @param presenterOps  reference passed by GenericPresenter
     *                      with available operations in PRESENTER layer
     */
    @Override
    public void onCreate(RequiredPresenterOps presenterOps) {
        Log.d(TAG, "onCreate() Model" );
        mPresenter = presenterOps;
    }

    /**
     * Hook method that receives destruction and change configurations events.
     * Shut down methods should be implemented here.
     * Called from {@link GenericPresenter#onDestroy(boolean)}
     *
     * @param isChangingConfiguration   Informs if Activity is being destroyed
     *                                  or changing its configuration
     *                                  true: configuration is changing
     *                                  false: a Activity is being destroyed
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        Log.d(TAG, "onDestrou(isChangingConfiguration:"+isChangingConfiguration+")");
    }

    /**
     * Recovers the PRESENTER layer reference
     * @return  Operations available on PRESENTER
     */
    public RequiredPresenterOps getPresenter() { return mPresenter; }
}
