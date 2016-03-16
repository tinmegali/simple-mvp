package com.tinmegali.mvp.mvp;

/**
 * <p>
 * Interface implemented by {@link GenericPresenter}. Contains
 * initializing and destruction methods called by VIEW layer in
 * the PRESENTER. Garantes the correct synchronization of MVP
 * with Activity lifecycle.
 * </p>
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
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 *
 * @param <RequiredViewOps> VIEW layer reference. Interface containing
 *                         operations available to PRESENTER
 *
 */
public interface PresenterOps<RequiredViewOps> extends ContextView {

    /**
     * Initialization method.
     * Called by initialization methods in VIEW, like
     *  {@link GenericMVPActivity#initialize(Class, Object)}
     *  {@link GenericMVPFragment#initialize(Class, Object)}.
     * Pass the VIEW reference with available operations to PRESENTER.
     * @param view  Current VIEW instance reference,
     *              with operations available
     */
    void onCreate(RequiredViewOps view);

    /**
     * Hook method called every time the VIEW change its configuration.
     * Called by reinitialization methods in VIEW, like
     *  {@link GenericMVPActivity#reinitialize(Class, Object)}
     *  {@link GenericMVPFragment#reinitialize(Class, Object)}
     * Responsible to send a new reference to the VIEW layer with operations
     * available to PRESENTER.
     *
     * @param view  New VIEW instance reference
     */
    void onConfigurationChanged(RequiredViewOps view);

    /**
     * Hook method called when the VIEW is being destroyed or
     * having its configuration changed.
     * Responsible to maintain MVP synchronized with Activity lifecycle.
     * Called by onDestroy methods of the VIEW layer, like:
     *  {@link GenericMVPActivity#onDestroy()}
     *  {@link GenericMVPFragment#onDestroy()}
     *
     * @param isChangingConfiguration   true: configuration changing
     *                                  false: being destroyed
     */
    void onDestroy(boolean isChangingConfiguration);

    /**
     * OnBack pressed Event, called by the VIEW.
     */
    void onBackPressed();



}
