package com.tinmegali.androidmvp.main.presenter;

import com.tinmegali.androidmvp.main.MVP_MainActivity;
import com.tinmegali.androidmvp.main.model.MainModel;
import com.tinmegali.mvp.mvp.GenericMVPActivity;
import com.tinmegali.mvp.mvp.GenericPresenter;

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
     *         Always call  {@link GenericPresenter#setView(Object)} to save a
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
     *         Always call {@link GenericPresenter#setView(Object)} to
     *         save the new instance of <code>RequiredViewOps</code>
     *     </li>
     *</ul>
     * @param view  The current VIEW instance
     */
    @Override
    public void onConfigurationChange(MVP_MainActivity.RequiredViewOps view) {
        setView(view);
    }


    /**
     * Called by {@link GenericMVPActivity} to inform an
     * <code>onBackPressed</code> event
     */
    @Override
    public void onBackPressed() {

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
        return saveName(nameTxt);
    }

    // For test purposes
    public boolean saveName(String txt){
        return getModel().saveName(txt);
    }

    @Override
    public boolean onNameSaved(String nameTxt) {
        return nameTxt != null;
    }

    @Override
    public boolean onNameCleared() {
        return true;
    }
}
