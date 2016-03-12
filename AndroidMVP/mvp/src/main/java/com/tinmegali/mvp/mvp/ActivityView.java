package com.tinmegali.mvp.mvp;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 25/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali">github</a>
 * ---------------------------------------------------
 *
 * Interface to be implemented by the Activity,
 * contains some basic common feedback operations
 */

public interface ActivityView extends ContextView {

    /**
     * Shows a toast in the Activity with a short time
     * @param msg   Message to show
     */
    void onShowToast(String msg);

    /**
     * Shows a {@link android.widget.Toast} in the Activity with custom time
     * @param msg       Message to show
     * @param duration  Time Length
     *                      {@link android.widget.Toast#LENGTH_SHORT}
     *                      {@link android.widget.Toast#LENGTH_LONG}
     */
    void onShowToast(String msg, int duration);

    /**
     * Shows a {@link Snackbar} in Activity with a short time
     * @param msg         Snackbar message
     * @param parentView  Snackbar parent view
     */
    void onShowSnackbar(String msg, View parentView);

    /**
     * Shows a {@link Snackbar} in Activity with custom time
     * @param msg         Snackbar message
     * @param parentView  Snackbar parent view
     * @param duration    Time Length
     *                      {@link Snackbar#LENGTH_SHORT}
     *                      {@link Snackbar#LENGTH_LONG}
     *                      {@link Snackbar#LENGTH_INDEFINITE}
     */
    void onShowSnackbar(String msg, View parentView, int duration);


    /**
     * Shows a given {@link Snackbar}.
     * Useful for snacks with custom actions.
     * @param snackbar  The Snackbar to show
     */
    void onShowSnackbar(Snackbar snackbar);
}
