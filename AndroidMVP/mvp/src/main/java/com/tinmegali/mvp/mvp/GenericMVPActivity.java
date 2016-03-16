package com.tinmegali.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.tinmegali.mvp.util.ActivityKeyBoardDetector;
/**
 * <p>
 * Generic Abstract Activity.
 * Works as a VIEW layer in the MVP pattern. <br>
 * Responsible to initialize the PRESENTER and to maintain
 * it synchronized with Activity lifecycle changes. <br>
 *
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
 * @param <RequiredViewOps> Interface with available
 *                         VIEW methods available to PRESENTER
 * @param <ProvidedPresenterOps> Interface with available
 *                              PRESENTER methods available to VIEW
 * @param <PresenterType> PRESENTER Object to be instantiated by the VIEW
 */
public abstract class GenericMVPActivity
                <RequiredViewOps,
                ProvidedPresenterOps,
                PresenterType extends PresenterOps<RequiredViewOps>>
        extends ActivityKeyBoardDetector
        implements ActivityView {

    protected final String TAG = getClass().getSimpleName();

    // Responsible to maintain objects state between config changes
    protected final StateMaintainer mStateMaintainer =
            new StateMaintainer( this.getFragmentManager(), TAG );

    // PRESENTER reference
    private PresenterType mPresenterInstance;

    /**
     * Hook method to initialize PRESENTER. Needs to be called in
     * {@link Activity#onCreate(Bundle)}.
     *
     * @param opsType   PRESENTER Object class
     * @param view      Interface with VIEW reference to PRESENTER
     */
    public void onCreate(Class<PresenterType> opsType, RequiredViewOps view ) {
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() calling for the first time.");
                initialize(opsType, view);
            } else {
                Log.d(TAG, "onCreate() calling more then once.");
                reinitialize(opsType, view);
            }
        } catch ( InstantiationException | IllegalAccessException e ) {
            Log.d(TAG, "onCreate() " + e );
            throw new RuntimeException( e );
        }
    }

    /**
     * Gets PRESENTER layer
     * @return  PRESENTER instance with operation availabe to VIEW
     */
    @SuppressWarnings("unchecked")
    public ProvidedPresenterOps getPresenter() { return (ProvidedPresenterOps) mPresenterInstance; }

    /**
     * @return State Maintainer instance
     */
    public StateMaintainer getStateMaintainer() { return mStateMaintainer; }

    /**
     * @return Activity Context
     */
    @Override
    public Context getActivityContext() {
        return this;
    }

    /**
     * @return Application Context
     */
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


    /**
     * Initialize PRESENTER layer.
     * Creates a new instance of the PRESENTER,
     * saves in the StateMaintainer and call {@link GenericPresenter#onCreate(Object)}
     * hook method.
     *
     * @param opsType   PRESENTER Object Class type
     * @param view      Current VIEW instance
     */
    private void initialize( Class<PresenterType> opsType, RequiredViewOps view )
            throws InstantiationException, IllegalAccessException{
        mPresenterInstance = opsType.newInstance();

        mStateMaintainer.put( opsType.getSimpleName(), mPresenterInstance );

        mPresenterInstance.onCreate(view);

    }

    /**
     * Recovers the PRESENTER and informs its instance that a
     * configuration change occurred, passing a VIEW reference
     * one more time.
     * Case the PRESENTER has been lost, another instance is created.
     *
     * @param opsType   PRESENTER Object Class type
     * @param view      Current VIEW instance
     */
    private void reinitialize( Class<PresenterType> opsType, RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {
        mPresenterInstance = mStateMaintainer.get( opsType.getSimpleName() );

        if ( mPresenterInstance == null ) {
            Log.w(TAG, "reinitialize: recreating presenter");
            initialize(opsType, view );
        } else {
            mPresenterInstance.onConfigurationChanged(view);
        }
    }

    /**
     * Show a Snackbar. Helper method to be called by Presenter
     * @param msg         Snackbar message
     * @param parentView  Snackbar parent view
     */
    public void onShowSnackbar(String msg, View parentView) {
        showSnackbar(msg, parentView);
    }

    /**
     * Show a Toast. Helper method to be called by Presenter
     * @param msg   Message to show
     */
    public void onShowToast(String msg) {
        showToast(msg);
    }

    /**
     * Show a Toast. Helper method to be called by Presenter
     * @param msg       Message to show
     * @param duration  Time Length
     *                      {@link android.widget.Toast#LENGTH_SHORT}
     */
    public void onShowToast(String msg, int duration) {
        showToast(msg, duration);
    }

    /**
     * Show a Snackbar. Helper method to be called by Presenter
     * @param msg         Snackbar message
     * @param parentView  Snackbar parent view
     * @param duration    Time Length
     *                      <code>Snackbar#LENGTH_SHORT</code>
     *                      <code>Snackbar#LENGTH_LONG</code>
     */
    public void onShowSnackbar(String msg, View parentView, int duration) {
        showSnackbar(msg, parentView, duration);
    }

    /**
     * Show a Snackbar. Helper method to be called by Presenter
     * @param snackbar  The Snackbar to show
     */
    public void onShowSnackbar(Snackbar snackbar) {
        showSnackbar(snackbar);
    }

}
