package com.tinmegali.androidmvp.main;

import android.view.View;

import com.tinmegali.androidmvp.common.mvp.ActivityView;
import com.tinmegali.androidmvp.common.mvp.ModelOps;
import com.tinmegali.androidmvp.common.mvp.PresenterOps;

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
 * Interface guarda-chuva, que armazena todas operações disponíveis
 * para realizar comunicações entre os diferentes layer
 * do Mdeol View Presenter
 */


public interface MVP_MainActivity {

    /**
     * Métodos obrigatórios em View, disponíveis para Presenter
     *      Presenter -> View
     */
    interface RequiredViewOps extends ActivityView {

    }


    /**
     * operações oferecidas ao layer View para comunicação com Presenter
     *      View -> Presenter
     */
    interface ProvidedPresenterOps extends PresenterOps<RequiredViewOps> {

    }

    /**
     * operações oferecidas pelo layer Presenter para comunicações com Model
     *      Model -> Presenter
     */
    interface RequiredPresenterOps {

    }

    /**
     * operações oferecidos pelo layer Model para comunicações com Presenter
     *      Presenter -> Model
     */
    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps>{

    }

}
