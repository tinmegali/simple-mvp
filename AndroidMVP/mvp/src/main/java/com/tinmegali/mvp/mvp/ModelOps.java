package com.tinmegali.mvp.mvp;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 25/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali">github</a>
 * ---------------------------------------------------
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 * framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 * Dr. Douglas Schmidth</a>
 * ---------------------------------------------------
 */

/**
 * Interface implemented by {@link GenericModel}. Contains
 * initializing and destruction methods called by VIEW layer in
 * all MVP objects. Garantes the correct synchronization of MVP
 * with Activity lifecycle.
 *
 * @param <RequiredPresenterOps>    PRESENTER operations available to MODEL
 *                              Acts as a callback to data operations.
 */
public interface ModelOps<RequiredPresenterOps> {

    /**
     * Initialization method. Receives the PRESENTER reference.
     * Called by {@link GenericPresenter#initialize(Class, Object)}
     *
     * @param presenterOps  PRESENTER reference, containing
     *                      operations available to MODEL
     */
    void onCreate(RequiredPresenterOps presenterOps);

    /**
     * Hook method called when the VIEW is being destroyed or
     * having its configuration changed.
     * Responsible to maintain MVP synchronized with Activity lifecycle.
     * Called by {@link GenericPresenter#onDestroy(boolean)}
     *
     * @param isChangingConfiguration   true: changing configuration
     *                                  false: being destroyed
     */
    void onDestroy(boolean isChangingConfiguration);
}
