package com.tinmegali.mvp.mvp;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 25/02/16.
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

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Classe base implementada por todos objetos Presenter.
 *
 * @param <RequiredPresenterOps>    interface a ser implementada, que contém
 *                                  requisitados pelo layer Model, para permitir
 *                                  a comunicação Model -> Presenter
 *
 * @param <ProvidedModelOps>        interface com métodos fornecidos pelo layer Model
 *                                  que permite a comunicação Presenter -> Model
 *
 * @param <ModelType>               objeto Classe do Model, para permitir ao Presenter
 *                                  instanciar um objeto deste tipo
 */
public abstract class GenericPresenter
        <RequiredPresenterOps,
                ProvidedModelOps, RequiredViewOps,
                ModelType extends ModelOps<RequiredPresenterOps> >
        implements PresenterOps<RequiredViewOps>{


    protected final String TAG = getClass().getSimpleName();

    // matém o tipo de destruição que está ocorrendo na Atividade
    // Se é definitiva ou devido à alteração de configuração
    private boolean mConfigurationChangeOccurred;

    // informa se a Atividade está ativa
    private boolean mIsRunning;

    // Referência para operações em View
    private WeakReference<RequiredViewOps> mView;

    // objeto Classe para instanciar o Model
    private ModelType mOpsInstance;

    /**
     * Método chamado pelo Presenter durante sua criação.
     * Deve a primeira operação a ser chamado na subscrição
     * do método {@link PresenterOps#onCreate(Object)}
     *
     * @param opsType   obj Classe do Model
     * @param presenter interface fornecida pelo Presenter
     *                  para possibilitar comunicação com Model
     */
    public void onCreate( Class<ModelType> opsType, RequiredPresenterOps presenter ) {
        mIsRunning = true;
        mConfigurationChangeOccurred = false;
        try {
            // inicializa o objeto Model
            initialize( opsType, presenter );
        } catch ( InstantiationException | IllegalAccessException e ) {
            Log.d(TAG, "handleConfiguration " + e);
            throw new RuntimeException( e );
        }
    }

    /**
     * Define referência para operações no layer View
     * Deve ser chamado em {@link PresenterOps#onCreate(Object)}
     * @param view  Referência para operações Presenter -> View
     */
    public void setView(RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    /**
     * Recupera referência View
     * @return  Operações disponíveis em View ou null,
     *          caso a View não esteja rodando ou passando por reconfig.
     */
    public RequiredViewOps getView() {
        if ( mIsRunning && !mConfigurationChangeOccurred && mView != null) {
            return mView.get();
        } else  {
            Log.w(TAG, "View indisponível.");
            return null;
        }
    }

    /**
     * primeiro método a ser chamado pelo Presenter
     * durante {@link PresenterOps#onDestroy(boolean)}
     *
     * @param isChangingConfiguration   informa se a destruição se
     *                                  deve em função de mudança de configuração
     */
    public void onDestroy(boolean isChangingConfiguration) {
        mIsRunning = isChangingConfiguration;
        mConfigurationChangeOccurred = isChangingConfiguration;
        mOpsInstance.onDestroy(isChangingConfiguration);
    }


    /**
     * Informa o estado atual de View
     * @return  true se está rodando
     */
    public boolean isViewRunning() { return mIsRunning; }

    /**
     * informa se ocorreu uma mudança de configuração
     * @return  true se ocorreu uma mudança
     */
    public boolean configurationsOccurred() { return mConfigurationChangeOccurred; }


    /**
     * Inicializa o objeto Model, criando um
     * nova instância e iniciando em Model as operação
     *
     * @param opsType   obj Classe para nova instância
     * @param presenter interface fornecida pelo Presenter
     *                  para comunicação Model -> Presenter
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void initialize( Class<ModelType> opsType, RequiredPresenterOps presenter )
            throws InstantiationException, IllegalAccessException {
        mOpsInstance = opsType.newInstance();

        mOpsInstance.onCreate( presenter );
    }

    /**
     * Retorna operações disponível em Model
     * @return  uma instância do objeto Model
     */
    @SuppressWarnings("unchecked")
    public ProvidedModelOps getModel() { return (ProvidedModelOps) mOpsInstance; }

}
