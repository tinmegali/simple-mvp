package com.tinmegali.androidmvp.common.mvp;

import android.support.design.widget.Snackbar;
import android.view.View;

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
 * Interface que garante acesso ao Presenter acesso aos contextos
 * e outros métodos obrigatórios em todas as atividades que fazem
 * parte do framework MVP.
 * Deve ser aplicada somente à Atividades
 */


public interface ActivityView extends ContextView {

    /**
     * Exibe Toast na Atividade com tempo curto
     */
    void onShowToast(String msg);

    /**
     * Exibe Toast na Atividade com tempo personalizado
     * @param duration  duração do tipo:
     *                      {@link android.widget.Toast#LENGTH_SHORT}
     *                      {@link android.widget.Toast#LENGTH_LONG}
     */
    void onShowToast(String msg, int duration);

    /**
     * Exibe Snackbar na Atividade com tempo curto
     */
    void onShowSnackbar(String msg, View parentView);

    /**
     * Exibe Snackbar na Atividade com tempo personalizado
     * @param duration  duração do tipo:
     *                      {@link Snackbar#LENGTH_SHORT}
     *                      {@link Snackbar#LENGTH_LONG}
     *                      {@link Snackbar#LENGTH_INDEFINITE}
     */
    void onShowSnackbar(String msg, View parentView, int duration);


    /**
     * Exibe snackbar recebida
     */
    void onShowSnackbar(Snackbar snackbar);
}
