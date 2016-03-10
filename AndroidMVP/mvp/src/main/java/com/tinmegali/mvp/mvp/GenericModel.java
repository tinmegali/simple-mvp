package com.tinmegali.mvp.mvp;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 25/02/16.
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

import android.util.Log;

/**
 * Genérico abstrato do layer MODEL no padrão MVP.
 *
 * Recupera referência dar operações disponível em PRESENTER
 * @param <RequiredPresenterOps>
 */
public abstract class GenericModel<RequiredPresenterOps> implements ModelOps<RequiredPresenterOps>  {

    private String TAG = getClass().getSimpleName();

    // Referência das operações em presenter
    private RequiredPresenterOps mPresenter;

    /**
     * Recupera referência para o layer PRESENTER
     * @param presenterOps  interface com o Presenter
     */
    @Override
    public void onCreate(RequiredPresenterOps presenterOps) {
        Log.d(TAG, "onCreate() Model" );
        mPresenter = presenterOps;
    }

    /**
     * Recebe info de destruição/reconstrução do layer VIEW
     * @param isChangingConfiguration   Informa se está ocorrendo
     *                                  uma mudança de configuração
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        Log.d(TAG, "onDestrou(isChangingConfiguration:"+isChangingConfiguration+")");
    }

    /**
    * Retorna operaçoes disponíveis em PRESENTER
     */
    public RequiredPresenterOps getPresenter() { return mPresenter; }
}
