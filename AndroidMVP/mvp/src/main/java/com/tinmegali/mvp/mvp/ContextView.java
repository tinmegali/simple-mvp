package com.tinmegali.mvp.mvp;

import android.content.Context;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 24/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali">github</a>
 * ---------------------------------------------------
 * Based on <a href="https://github.com/douglascraigschmidt/POSA-15/tree/master/ex/AcronymExpander/src/vandy/mooc">
 *     framework MVP</a> developed by
 * <a href="https://github.com/douglascraigschmidt">
 *     Dr. Douglas Schmidth</a>
 * ---------------------------------------------------
 *
 * Interface that grants access to Contexts
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
