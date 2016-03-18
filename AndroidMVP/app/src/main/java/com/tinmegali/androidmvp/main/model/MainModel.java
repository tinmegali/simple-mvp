package com.tinmegali.androidmvp.main.model;

import android.os.AsyncTask;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.presenter.MainPresenter;
import com.tinmegali.mvp.mvp.GenericModel;
import com.tinmegali.mvp.mvp.PresenterOps;

import javax.inject.Inject;

/**
 * <p>
 * Layer MODEL from Model View Presenter (MVP) pattern. <br/>
 * Responsible to deal with all business logic and data in general. <br/>
 * </p>
 *
 * Created by Tin Megali on 25/02/16. <br/>
 * Project: AndroidMVP </br>
 * --------------------------------------------------- <br />
 * <a href="http://www.tinmegali.com">tinmegali.com</a> <br/>
 * <a href="http://www.github.com/tinmegali>github</a> <br />
 * ---------------------------------------------------
 * <p>
 * Based on
 * <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 * framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 * Dr. Douglas Schmidth</a>
 * </p>
 */

public class MainModel extends GenericModel<MVP_MainActivity.RequiredPresenterOps>
        implements MVP_MainActivity.ProvidedModelOps {






    public MainModel() {
    }

    /**
     * Method that recovers a reference to the PRESENTER
     * <ul>
     *     <li>You must ALWAYS call {@link super#onCreate(Object)} here </li>
     * </ul>
     * @param presenterOps Presenter interface
     */
    @Override
    public void onCreate(MVP_MainActivity.RequiredPresenterOps presenterOps) {
        super.onCreate(presenterOps);
    }

    /**
     * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction. <br/>
     * Usefull for kill/stop activities that could be running on the background
     * Threads
     * @param isChangingConfiguration   Informs that a change is occurring on the configuration
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
    }



    String mName;

    @Override
    public boolean clearName() {
        mName = null;
        return getPresenter().onNameCleared();
    }

    @Override
    public boolean saveName(final String nameTxt) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                mName = nameTxt;
                try {
                    Thread.sleep(2000);
                    return mName;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String name) {
                if ( name != null)
                    getPresenter().onNameSaved(name);
            }
        }.execute();
        return true;
    }

    // just for tests
    public String getName(){
        return mName;
    }
}
