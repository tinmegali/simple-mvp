package com.tinmegali.androidmvp.common.mvp;

/**
 * Atividade genérica, que funciona como um framework mediando o acesso
 * com o Presenter no padrão Model View Presenter (MVP), além de lidar
 * com operações de ciclo de vida. Implementa {@link ContextView},
 * garantindo ao Presenter acesso aos contextos.
 *
 * ---------------------------------------------------
 * Created by Tin Megali on 24/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
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

import com.tinmegali.androidmvp.common.ActivityKeyBoardDetector;

/**
 * Atividade genérica, que funciona como um framework mediando o acesso
 * com o Presenter no padrão Model View Presenter (MVP), além de lidar
 * com operações de ciclo de vida. Implementa {@link ContextView},
 * garantindo ao Presenter acesso aos contextos.
 * Extende {@link ActivityKeyBoardDetector}, adicionando funções de
 * para detectar presença do softKeyboard na tela
 *
 * @param <RequiredViewOps> classe ou interface que define
 *     os métodos do Presenter disponíveis para o layer View
 * @param <ProvidedPresenterOps> classe ou interface que
 *     define os métodos do View disponíveis para o Presenter.
 * @param <PresenterType> a classe utilizada pelo framework
 *     para implementar um objeto Presenter
 */
public abstract class GenericMVPActivity
                <RequiredViewOps,
                ProvidedPresenterOps,
                PresenterType extends PresenterOps<RequiredViewOps>>
        extends ActivityKeyBoardDetector
        implements ActivityView {

    protected final String TAG = getClass().getSimpleName();

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    protected final StateMaintainer mStateMaintainer =
            new StateMaintainer( this.getFragmentManager(), TAG );

    // Presenter
    private PresenterType mPresenterInstance;

    /**
     * Inicia e reinicia o Presenter. Este método precisa ser chamado
     * após {@link Activity#onCreate(Bundle)}
     * @param opsType   Classe utilizada para criar o Presenter
     * @param view      Referência no Presenter para {@link RequiredViewOps}
     */
    public void onCreate(Class<PresenterType> opsType, RequiredViewOps view ) {
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() chamado pela primera vez");
                initialize(opsType, view);
            } else {
                Log.d(TAG, "onCreate() chamado mais de uma vez");
                reinitialize(opsType, view);
            }
        } catch ( InstantiationException | IllegalAccessException e ) {
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
     * Retorna contextos
     */
    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


    /**
     * Inicializa os objetos relevantes para o MVP.
     * Cria uma instância do Presenter, salva o presenter
     * no {@link StateMaintainer} e informa à instância do
     * presenter que objeto foi criado.
     * @param opsType   Classe do presenter, para criação da instância
     * @param view      Operações no View exigidas pelo Presenter
     */
    private void initialize( Class<PresenterType> opsType, RequiredViewOps view )
            throws InstantiationException, IllegalAccessException{
        mPresenterInstance = opsType.newInstance();

        mStateMaintainer.put( opsType.getSimpleName(), mPresenterInstance );

        mPresenterInstance.onCreate(view);

    }

    /**
     * Recupera o presenter e informa à instância que houve uma mudança
     * de configuração no View.
     * Caso o presenter tenha sido perdido, uma nova instância é criada
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
