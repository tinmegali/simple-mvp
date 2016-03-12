package com.tinmegali.mvp.mvp;

import android.content.Context;

/**
 * <p>Interface that grants access to Contexts</p>
 * <p>
 * Created by Tin Megali on 24/02/16. <br/>
 * Project: AndroidMVP <br/>
 *
 * <a href="http://www.tinmegali.com">www.tinmegali.com</a>
 * </p>
 * --------------------------------------------------- <br/>
 * <p>
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 *     framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 *     Dr. Douglas Schmidth</a>
 * </p>
 *
 * @see <a href="https://github.com/tinmegali/Android-Model-View-Presenter-MVP">Project's Git</a> <br />
 *
 */

public interface ContextView {

    /**
     * Access to application {@link Context}
     * @return  application context
     */
    Context getApplicationContext();

    /**
     * Access to current activity {@link Context}
     * @return  activity context
     */
    Context getActivityContext();
}
