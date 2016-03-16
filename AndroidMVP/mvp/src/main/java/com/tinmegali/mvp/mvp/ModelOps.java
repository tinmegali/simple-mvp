package com.tinmegali.mvp.mvp;

/**
 * <p>
 * Interface implemented by {@link GenericModel}. <br>
 * Contains
 * initializing and destruction methods called by VIEW layer in
 * all MVP objects. Garantes the correct synchronization of MVP
 * with Activity lifecycle.
 * <p>
 * <strong>IMPORTANT: View Object should implement <code> RequiredViewOps </code></strong>
 * </p>
 *
 * <p>
 * Created by Tin Megali on 24/02/16. <br>
 * Project: AndroidMVP <br>
 *
 * <a href="http://www.tinmegali.com">www.tinmegali.com</a>
 * </p>
 * --------------------------------------------------- <br>
 * <p>
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 *     framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 *     Dr. Douglas Schmidth</a>
 * </p>
 *
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 *
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
