package com.tinmegali.mvp.mvp;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 24/02/16.
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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.tinmegali.mvp.ActivityKeyBoardDetector;
/**
 * Generic Abstract Activity.
 * Works as a VIEW layer in the MVP pattern.
 * Responsible to initialize the PRESENTER and to maintain
 * it synchronized with Activity lifecycle changes.
 * IMPORTANT: View Object should implement {@param RequiredViewOps}
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
            mPresenterInstance.onConfigurationChange( view );
        }
    }

}
