package com.tinmegali.androidmvp.main.model;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.mvp.mvp.GenericModel;

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
 * TODO english comments
 * Layer MODEL no padrão Model View Presenter (MVP)
 * Responsável por lidar com qualquer lógica de dados.
 * Exibição de qualquer resultado na UI ou qualquer comunicação
 * com layer VIEW deve ser intermediada pelo PRESENTER
 */

public class MainModel extends GenericModel<MVP_MainActivity.RequiredPresenterOps>
        implements MVP_MainActivity.ProvidedModelOps {

    /**
     * Method that recovers a reference to the PRESENTER
     * - You must ALWAYS call {@link super#onCreate(Object)} here
     * @param presenterOps Presenter interface
     *
     * Método que recupera referência ao layer PRESENTER
     * 1 - {@link super#onCreate(Object)} deve sempre deve ser chamado
     */
    @Override
    public void onCreate(MVP_MainActivity.RequiredPresenterOps presenterOps) {
        super.onCreate(presenterOps);
    }

    /**
     * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
     * Usefull for kill/stop activities that could be running on the background
     * Threads
     * @param isChangingConfiguration   Informs that a change is occurring on the configuration
     *
     *
     * Disparado pelo layer PRESENTER quando o VIEW passa por uma
     * reconstrução/destruição. Útil como referência para parar tarefas
     * sendo realizadas em background Threads
     *
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }
}
