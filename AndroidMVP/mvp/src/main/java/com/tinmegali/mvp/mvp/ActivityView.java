package com.tinmegali.mvp.mvp;

import android.view.View;

/**
 * <p>
 * Interface to be implemented by the Activity,
 * contains some basic common feedback operations
 * </p>
 * <p>
 * Created by Tin Megali on 24/02/16. <br>
 * Project: AndroidMVP <br>
 *
 * <a href="http://www.tinmegali.com">www.tinmegali.com</a>
 *
 * @see <a href="https://github.com/tinmegali/simple-mvp">Project's Git</a> <br>
 * @see <a href="https://github.com/tinmegali/simple-mvp/tree/master/AndroidMVP/app">Sample Application</a>
 * @see <a href="https://github.com/tinmegali/simple-mvp/blob/master/AndroidMVP/app/src/main/java/com/tinmegali/androidmvp/main/MVP_MainActivity.java">
 *         Sample MVP interface
 *     </a>
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
     * Shows a <code>Snackbar</code> in Activity with a short time
     * @param msg         Snackbar message
     * @param parentView  Snackbar parent view
     */
    void onShowSnackbar(String msg, View parentView);

    /**
     * Shows a <code>Snackbar</code> in Activity with custom time
     * @param msg         Snackbar message
     * @param parentView  Snackbar parent view
     * @param duration    Time Length
     *                      <code>Snackbar#LENGTH_SHORT</code>
     *                      <code>Snackbar#LENGTH_LONG</code>
     *                      <code>Snackbar#LENGTH_INDEFINITE</code>
     */
    void onShowSnackbar(String msg, View parentView, int duration);


    /**
     * Shows a given <code>Snackbar</code>
     * Useful for snacks with custom actions.
     * @param snackbar  The Snackbar to show
     */
    void onShowSnackbar(android.support.design.widget.Snackbar snackbar);
}
