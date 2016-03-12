package com.tinmegali.androidmvp.main.model;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.mvp.mvp.GenericModel;

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
}
