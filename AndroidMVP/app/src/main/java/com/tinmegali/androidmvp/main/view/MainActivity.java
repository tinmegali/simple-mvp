package com.tinmegali.androidmvp.main.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tinmegali.androidmvp.R;
import com.tinmegali.androidmvp.common.mvp.GenericMVPActivity;
import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;

/**
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
 *
 * Layer VIEW no padrão Model View Presenter (MVP)
 *
 * Realiza uma comunicação direta com PRESENTER, que faz intermediação
 * com o layer MODEL e tras os resultados para VIEW.
 */

public class MainActivity
        extends
            GenericMVPActivity<MVP_MainActivity.RequiredViewOps,
                                    MainPresenter,
                                    MainPresenter>
        implements
            MVP_MainActivity.RequiredViewOps{

    /**
     * Método padrão de criação de Atividades
     * 1 - {@link super#onCreate(Class, Object)} deve obrigatóriamente
     *     ser chamado na sequência ao método {@link super#onCreate(Bundle)}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(MainPresenter.class,this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    /**
     *
     * Operações disponíveis para layer PRESENTER
     *
     */


    @Override
    public void onShowSnackbar(String msg, View parentView) {
        showSnackbar(msg, parentView);
    }

    @Override
    public void onShowToast(String msg) {
        showToast(msg);
    }

    @Override
    public void onShowToast(String msg, int duration) {
        showToast(msg, duration);
    }

    @Override
    public void onShowSnackbar(String msg, View parentView, int duration) {
        showSnackbar(msg, parentView, duration);
    }

    @Override
    public void onShowSnackbar(Snackbar snackbar) {
        showSnackbar(snackbar);
    }
}
