package com.tinmegali.androidmvp.main.presenter;

import android.util.Log;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.mvp.mvp.ContextView;
import com.tinmegali.mvp.mvp.GenericMVPActivity;
import com.tinmegali.mvp.mvp.GenericPresenter;

import javax.inject.Inject;

/**

 * <p>
 * Layer PRESENTER from Model View Presenter (MVP) Pattern. <br/>
 * Mediates the comunication between layers VIEW and MODEL.
 * </p>
 * <p>
 * Layer Presenter no padrão Model View Presenter (MVP). <br/>
 *
 * Created by: Tin Megali on 25/02/16. <br/>
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

public class MainPresenter
        extends GenericPresenter<MVP_MainActivity.RequiredPresenterOps,
                                MVP_MainActivity.ProvidedModelOps, MVP_MainActivity.RequiredViewOps,
                                MainModel>
        implements
            MVP_MainActivity.RequiredPresenterOps,
            MVP_MainActivity.ProvidedPresenterOps
{

    public static final String TAG = MainPresenter.class.getSimpleName();

    public MVP_MainActivity.ProvidedModelOps mModel;

    @Inject
    public MainPresenter(MainModel model) {
//        Log.d(TAG, "constructor()");
        model.onCreate(this);
        mModel = model;
    }

    /**
     * Operation called during VIEW creation in
     * {@link com.tinmegali.mvp.mvp.GenericMVPActivity#onCreate(Class, Object)} </br>
     * Responsible to initialize MODEL.
     * <ul>
     *     <li>
     *         Always call {@link GenericPresenter#onCreate(Class, Object)} to
     *         initialize the object
     *     </li>
     *     <li>
     *         Always call  {@link GenericPresenter#setView(ContextView)} to save a
     *         <code>RequiredViewOps</code> reference
     *     </li>
     * </ul>
     * @param view  The current VIEW instance
     */
    @Override
    public void onCreate(MVP_MainActivity.RequiredViewOps view) {
        super.onCreate(MainModel.class, this);
        // super.onCreate(<Model.class>, <RequiredPresenterOps>);
        setView( view );
    }

    /**
     * Operation called by VIEW after its reconstruction.
     *<ul>
     *     <li>
     *         Always call {@link GenericPresenter#setView(ContextView)} to
     *         save the new instance of <code>RequiredViewOps</code>
     *     </li>
     *</ul>
     * @param view  The current VIEW instance
     */
    @Override
    public void onConfigurationChanged(MVP_MainActivity.RequiredViewOps view) {
        setView(view);
    }


    /**
     * Helper method to inform Presenter that a <code>onBackPressed</code> event occurred
     * Called by {@link GenericMVPActivity}
     */
    @Override
    public void onBackPressed() {

    }

    @Override
    public MVP_MainActivity.ProvidedModelOps getModel() {
        return mModel;
    }

    @Override
    public boolean clickClearName() {
        return clearName();
    }

    // For test purposes
    public boolean clearName(){
        return getModel().clearName();
    }

    @Override
    public boolean clickSaveName(String nameTxt) {
//        Log.d(TAG, "clickSaveName("+nameTxt+")");
        return saveName(nameTxt);
    }

    // For test purposes
    public boolean saveName(String txt){
        return getModel().saveName(txt);
    }

    @Override
    public boolean onNameSaved(String nameTxt) {
        Log.d(TAG, "onNameSaved("+nameTxt+")");
        return nameTxt != null;
    }

    @Override
    public boolean onNameCleared() {
        return true;
    }
}
