package com.tinmegali.mvp.mvp;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 *
 * <p>A Model View Presenter Library using plain and simple interfaces.</p>
 *
 * <p>
 * Abstract class of PRESENTER layer on MVP pattern. <br>
 * Should be extended by any PRESENTER object
 * <strong>IMPORTANT: PRESENTER pbject should implement
 *  <code>RequiredPresenterOps</code> and <code>ProvidedModelOps</code></strong>
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
 * @param <RequiredPresenterOps>Interface with available PRESENTER
 *                              operation to the MODEL layer
 *
 * @param <ProvidedModelOps>    Interface with available MODEL
 *                              operations available to PRESENTER
 *
 * @param <RequiredViewOps>     Interface with available VIEW
 *                              operations available to PRESENTER
 *
 * @param <ModelType>           MODEL object to be instantiated by
 *                              the PRESENTER
 */

public abstract class GenericPresenter
                <RequiredPresenterOps,
                ProvidedModelOps, RequiredViewOps extends ContextView,
                ModelType extends ModelOps<RequiredPresenterOps> >
        implements PresenterOps<RequiredViewOps>
{


    protected final String TAG = getClass().getSimpleName();

    // Reference to the destruction kind that is occurring in VIEW
    private boolean mConfigurationChangeOccurred;

    // Informs if VIEW is active
    private boolean mIsRunning;

    // Reference to VIEW operations
    private WeakReference<RequiredViewOps> mView;

    // MODEL object to be instantiated
    private ModelType mOpsInstance;

    /**
     * Method that NEEDS TO BE CALLED by the PRESENTER object
     * in the {@link PresenterOps#onCreate(Object)} method.
     * Initialize MODEL and PRESENTER.
     *
     * @param opsType   Class of the MODEL object
     * @param presenter Interface with provided PRESENTER operations
     *                  available to MODEL
     */
    public void onCreate( Class<ModelType> opsType, RequiredPresenterOps presenter ) {
        mIsRunning = true;
        mConfigurationChangeOccurred = false;
        try {
            // initialized MODEL
            initialize( opsType, presenter );
        } catch ( InstantiationException | IllegalAccessException e ) {
            Log.d(TAG, "handleConfiguration " + e);
            throw new RuntimeException( e );
        }
    }

    /**
     * Define a VIEW reference available to PRESENTER.
     * Should be called i {@link PresenterOps#onCreate(Object)}
     *
     * @param view  VIEW operations available to PRESENTER
     */
    public void setView(RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    /**
     * Recovers the VIEW reference, returning NULL if layers isn't available.
     * @return  VIEW reference or NULL
     */
    public RequiredViewOps getView() {
        if ( mIsRunning && !mConfigurationChangeOccurred && mView != null) {
            return mView.get();
        } else  {
            Log.w(TAG, "View unavailable.");
            return null;
        }
    }

    /**
     * Recover the application Context
     * @return Application Context
     */
    public Context getApplicationContext() {
        return mView.get().getApplicationContext();
    }

    /**
     * Recover the Activity Context
     * @return  Activity Context
     */
    public Context getActivityContext() {
        return mView.get().getActivityContext();
    }

    /**
     * Hook method informing of destruction of the VIEW.
     * Should be subscribed by the PRESENTER object and called by itself
     * as a super method.
     *
     * @param isChangingConfiguration   true: VIEW changing configuration
     *                                  false: VIEW being destroyed
     */
    public void onDestroy(boolean isChangingConfiguration) {
        mIsRunning = isChangingConfiguration;
        mConfigurationChangeOccurred = isChangingConfiguration;
        mOpsInstance.onDestroy(isChangingConfiguration);
    }


    /**
     * Informs actual VIEW state
     * @return  true: VIEW is active
     *          false: VIEW is destroyed
     */
    public boolean isViewRunning() { return mIsRunning; }

    /**
     * Informs if occurred a configuration change
     * @return  true: a configuration change occured
     */
    public boolean configurationsOccurred() { return mConfigurationChangeOccurred; }


    /**
     * Initialized the MODEL object, creating a new MODEL instance
     *
     * @param opsType   MODEL object Class
     * @param presenter Interface given to MODEL with available
     *                  PRESENTER operations
     *                         MODEL -> PRESENTER
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void initialize( Class<ModelType> opsType, RequiredPresenterOps presenter )
            throws InstantiationException, IllegalAccessException {
        mOpsInstance = opsType.newInstance();

        mOpsInstance.onCreate( presenter );
    }

    /**
     * Returns available MODEL methods
     * @return  a MODEL object instance
     */
    @SuppressWarnings("unchecked")
    public ProvidedModelOps getModel() { return (ProvidedModelOps) mOpsInstance; }

    /**
     * Added for test purposes. Sets the given Model
     * without the need to cal {@link #onCreate(Class, Object)}.
     * IMPORTANT: Use ONLY FOR TESTS.
     * @param model Model instance
     */
    public void testWithModel(ModelType model) {
        mOpsInstance = model;
    }
}
