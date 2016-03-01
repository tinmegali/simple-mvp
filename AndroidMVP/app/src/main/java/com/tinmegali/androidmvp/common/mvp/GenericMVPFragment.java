package com.tinmegali.androidmvp.common.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 01/03/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */

/**
 * Fragmento genérico, que funciona como um framework mediando o acesso
 * com o Presenter no padrão Model View Presenter (MVP), além de lidar
 * com operações de ciclo de vida.
 *
 * @param <RequiredActivityOps>      classe ou interface que define operações
 *                                      obrigatórias para comunicação com Activity
 * @param <RequiredViewOps>         classe ou interface que define
 *                                      operações a serem implementadas pela View
 * @param <ProvidedPresenterOps>    classe ou interface que define
 *                                      operações oferecidas pelo Presenter
 * @param <PresenterType>           Objeto Presenter
 */
public class GenericMVPFragment<
        RequiredActivityOps,
        RequiredViewOps,
        ProvidedPresenterOps,
        PresenterType extends PresenterOps<RequiredViewOps>>
        extends Fragment
        implements ContextView {

    protected final String TAG = getClass().getSimpleName();

    // Presenter
    private PresenterType mPresenterInstance;

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    protected StateMaintainer mStateMaintainer;

    // Interface de operaçãoes frag -> Activity
    protected WeakReference<RequiredActivityOps> mActivity;


    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mActivity = new WeakReference<>( (RequiredActivityOps) getActivity() );
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement RequiredActivityOps" );
        }
    }

    /**
     * Inicia e reinicia o Presenter. Este método precisa ser chamado
     * após {@link Activity#onCreate(Bundle)}
     * @param opsType   Classe utilizada para criar o Presenter
     * @param view      Referência no Presenter para {@link RequiredViewOps}
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
     * Retorna o layer Presenter já inicializado
     */
    @SuppressWarnings("unchecked")
    public ProvidedPresenterOps getPresenter() { return (ProvidedPresenterOps) mPresenterInstance; }

    /**
     * Retorna instância do StateMaintainer
     */
    public StateMaintainer getStateMaintainer() { return mStateMaintainer; }


    /**
     * Inicializa os objetos relevantes para o MVP.
     * Cria uma instância do Presenter, salva o presenter
     * no {@link StateMaintainer} e informa à instância do
     * presenter que objeto foi criado.
     * @param opsType   Classe do presenter, para criação da instância
     * @param view      Operações no View exigidas pelo Presenter
     */
    private void initialize( Class<PresenterType> opsType, RequiredViewOps view )
            throws java.lang.InstantiationException, IllegalAccessException{
        mPresenterInstance = opsType.newInstance();

        mStateMaintainer.put(opsType.getSimpleName(), mPresenterInstance);

        mPresenterInstance.onCreate( view );

    }

    /**
     * Recupera o presenter e informa à instância que houve uma mudança
     * de configuração no View.
     * Caso o presenter tenha sido perdido, uma nova instância é criada
     */
    private void reinitialize( Class<PresenterType> opsType, RequiredViewOps view)
            throws java.lang.InstantiationException, IllegalAccessException {
        mPresenterInstance = mStateMaintainer.get( opsType.getSimpleName() );

        if ( mPresenterInstance == null ) {
            initialize( opsType, view );
        } else {
            mPresenterInstance.onConfigurationChange( view );
        }
    }

    /**
     * Retorna contextos
     */
    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }
}