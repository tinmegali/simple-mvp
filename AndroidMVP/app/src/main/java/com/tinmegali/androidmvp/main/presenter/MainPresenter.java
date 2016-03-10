package com.tinmegali.androidmvp.main.presenter;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.mvp.mvp.GenericPresenter;

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
 * Layer Presenter no padrão Model View Presenter (MVP)
 *
 * Cumpre o papel de mediador entre o layer View (Activity, Frags e uis)
 * e o layer Model (operações de dados).
 */

public class MainPresenter
        extends GenericPresenter<MVP_MainActivity.RequiredPresenterOps,
                                MVP_MainActivity.ProvidedModelOps, MVP_MainActivity.RequiredViewOps,
                                MainModel>
        implements
            MVP_MainActivity.RequiredPresenterOps,
            MVP_MainActivity.ProvidedPresenterOps
{

    /**
     * Operação invocada durante a criação de View em
     * {@link com.tinmegali.mvp.mvp.GenericMVPActivity#onCreate(Class, Object)}
     * Responsável por inicializar Model e armazenar referência das
     * opeações em View.
     *
     * 1 - é obrigatório invocar {@link GenericPresenter#onCreate(Class, Object)}
     * antes de qualquer atividade
     * 2 - é obrigatório invocar {@link GenericPresenter#setView(Object)}
     * passando {@param view}
     *
     * @param view  A instância atual
     */
    @Override
    public void onCreate(MVP_MainActivity.RequiredViewOps view) {
        super.onCreate(MainModel.class, this);
        // super.onCreate(<Model.class>, <RequiredPresenterOps>);
        setView( view );
    }

    /**
     * Operação invocada após a reconstrução da View
     *
     * 1 - é obrigatório invocar {@link GenericPresenter#setView(Object)}
     * passando {@param view}
     * @param view  A instância View atual
     */
    @Override
    public void onConfigurationChange(MVP_MainActivity.RequiredViewOps view) {
        setView(view);
    }


    /**
     * Disparado pela {@link com.tinmegali.mvp.mvp.GenericMVPActivity} para informar um evento onBackPressed
     */
    @Override
    public void onBackPressed() {

    }
}
