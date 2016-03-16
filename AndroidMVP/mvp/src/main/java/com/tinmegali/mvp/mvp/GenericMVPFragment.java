package com.tinmegali.mvp.mvp;

import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * <p>
 * Generic Abstract Fragment.
 * Works as a VIEW layer in the MVP pattern. <br>
 * Responsible to initialize the PRESENTER and to maintain
 * it synchronized with Activity lifecycle changes. <br>
 * <strong>IMPORTANT: View Object should implement <code>RequiredViewOps</code></strong>
 * </p>
 * <p>
 * Created by Tin Megali on 24/02/16. <br>
 * Project: AndroidMVP <br>
 *
 * <a href="http://www.tinmegali.com">www.tinmegali.com</a>
 * </p>
 *
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
 *
 * @param <RequiredActivityOps>  Interface that define operation to be executed
 *                             on the Activity
 * @param <RequiredViewOps> Interface with available
 *                         VIEW methods available to PRESENTER
 * @param <ProvidedPresenterOps> Interface with available
 *                              PRESENTER methods available to VIEW
 * @param <PresenterType> PRESENTER Object to be instantiated by the VIEW
 */
public abstract class GenericMVPFragment<
        RequiredActivityOps,
        RequiredViewOps,
        ProvidedPresenterOps,
        PresenterType extends PresenterOps<RequiredViewOps>>
        extends android.support.v4.app.Fragment
        implements ContextView {

    protected final String TAG = getClass().getSimpleName();

    // PRESENTER reference
    private PresenterType mPresenterInstance;

    // Responsible to maintain objects state between config changes
    protected StateMaintainer mStateMaintainer;

    // Activity operations available
    protected WeakReference<RequiredActivityOps> mActivity;

    /**
     * Subscribes onAttach to get a reference from current Activity
     * and its available operations to Fragment
     * @param context   Activity context
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mActivity = new WeakReference<>( (RequiredActivityOps) getActivity() );
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement <RequiredActivityOps>" );
        }
    }

    /**
     * Hook method to initialize PRESENTER. Needs to be called in
     * <code>Fragment#onCreate(Bundle)</code>
     *
     * @param opsType   PRESENTER Object class
     * @param view      Interface with VIEW reference to PRESENTER
     */
    public void onCreate(Class<PresenterType> opsType, RequiredViewOps view ) {
        if ( mStateMaintainer == null)
            mStateMaintainer =
                    new StateMaintainer(getActivity().getFragmentManager(), TAG + "_retainer" );
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "First time calling onCreate()");
                initialize(opsType, view);
            } else {
                Log.d(TAG, "Second (or subsequent) time calling onCreate()");
                reinitialize(opsType, view);
            }
        } catch ( java.lang.InstantiationException | IllegalAccessException e ) {
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
     * Initialize PRESENTER layer.
     * Creates a new instance of the PRESENTER,
     * saves in the StateMaintainer and call {@link GenericPresenter#onCreate(Object)}
     * hook method.
     *
     * @param opsType   PRESENTER Object Class type
     * @param view      Current VIEW instance
     */
    private void initialize( Class<PresenterType> opsType, RequiredViewOps view )
            throws java.lang.InstantiationException, IllegalAccessException{
        mPresenterInstance = opsType.newInstance();

        mStateMaintainer.put(opsType.getSimpleName(), mPresenterInstance);

        mPresenterInstance.onCreate( view );

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
            throws java.lang.InstantiationException, IllegalAccessException {
        mPresenterInstance = mStateMaintainer.get( opsType.getSimpleName() );

        if ( mPresenterInstance == null ) {
            initialize( opsType, view );
        } else {
            mPresenterInstance.onConfigurationChanged(view);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroy() {
        super.onDestroy();
        ((PresenterOps<RequiredViewOps>)getPresenter())
                .onDestroy(getActivity().isChangingConfigurations());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onPause() {
        super.onPause();
        ((PresenterOps<RequiredViewOps>)getPresenter())
                .onDestroy(getActivity().isChangingConfigurations());
    }

    /**
     * @return Activity Context
     */
    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    /**
     * @return Application Context
     */
    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }
}