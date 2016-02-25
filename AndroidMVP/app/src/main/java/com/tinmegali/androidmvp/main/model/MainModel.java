package com.tinmegali.androidmvp.main.model;

import com.tinmegali.androidmvp.common.mvp.GenericModel;
import com.tinmegali.androidmvp.main.MVP_MainActivity;

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
 *
 * Layer MODEL no padrão Model View Presenter (MVP)
 * Responsável por lidar com qualquer lógica de dados.
 * Exibição de qualquer resultado na UI ou qualquer comunicação
 * com layer VIEW deve ser intermediada pelo PRESENTER
 */

public class MainModel extends GenericModel<MVP_MainActivity.RequiredPresenterOps>
        implements MVP_MainActivity.ProvidedModelOps {

    /**
     * Método que recupera referência ao layer PRESENTER
     * 1 - {@link super#onCreate(Object)} deve sempre deve ser chamado
     * @param presenterOps  interface com o Presenter
     */
    @Override
    public void onCreate(MVP_MainActivity.RequiredPresenterOps presenterOps) {
        super.onCreate(presenterOps);
    }

    /**
     * Disparado pelo layer PRESENTER quando o VIEW passa por uma
     * reconstrução/destruição. Útil como referência para parar tarefas
     * sendo realizadas em background Threads
     *
     * @param isChangingConfiguration   Informa se está ocorrendo uma
     *                                  mudança de configuração
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }
}
