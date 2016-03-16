package com.tinmegali.mvp.util;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

/**
 * Created by Tin Megali on 24/02/16. <br>
 * Project: AndroidMVP <br>
 *
 * <a href="http://www.tinmegali.com">www.tinmegali.com</a> <br>
 * <a href="https://github.com/tinmegali/Android-Model-View-Presenter-MVP">Project's Git</a> <br>
 * --------------------------------------------------- <br>
 * <br><br>
 * Object to add to an Activity the capability to detect the presence
 * of the SoftKeyboard. <br>
 *
 */
public abstract class ActivityKeyBoardDetector extends AppCompatActivity {

    private final String TAG = ActivityKeyBoardDetector.class.getSimpleName();

    @Override
    protected void onDestroy() {
        // Remove LayoutChange listener
        stopDetectRootViewChanges();
        super.onDestroy();
    }

    /**
     * Shows a toast in the Activity with a short time
     * @param msg   Message to show
     */
    protected void showToast(String msg) {
        Toast.makeText(
                getApplicationContext(), msg, Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Shows a <code>android.widget.Toast</code> in the Activity with custom time
     * @param msg       Message to show
     * @param duration  Time Length
     *                      <code>android.widget.Toast#LENGTH_SHORT</code>
     *                      <code>@link android.widget.Toast#LENGTH_LONG</code>
     */
    protected void showToast(String msg, final int duration) {
        Toast.makeText(
                getApplicationContext(), msg, Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Shows a <code>Snackbar</code> in Activity with a short time
     * @param msg         Snackbar message
     * @param view  Snackbar parent view
     */
    protected void showSnackbar(String msg, View view){
        android.support.design.widget.Snackbar.make(view, msg, android.support.design.widget.Snackbar.LENGTH_SHORT).show();
    }


    /**
     * Shows a <code>Snackbar</code> in Activity with custom time
     * @param msg         Snackbar message
     * @param view  Snackbar parent view
     * @param duration    Time Length
     *                      <code>Snackbar#LENGTH_SHORT</code>
     *                      <code>Snackbar#LENGTH_LONG</code>
     *                      <code>Snackbar#LENGTH_INDEFINITE</code>
     */
    protected void showSnackbar(String msg, View view, int duration){
        android.support.design.widget.Snackbar.make( view, msg, duration).show();
    }


    /**
     * Shows a given <code>Snackbar</code>
     * Useful for snacks with custom actions.
     * @param snackbar  The Snackbar to show
     */
    protected void showSnackbar(android.support.design.widget.Snackbar snackbar){
        snackbar.show();
    }


    /**
     * Detecting Layout Root changes
     * <ul>
     * <li>{@link #startDetectRootViewChanges(View)} must be called on {@link #onCreate(Bundle)}</li>
     * <li>Detect if SoftKeyboard is On/Off
     *      {@link #onKeyBoardOn()} {@link #onKeyBoardOff()}</li>
     * <li>Register event for layout changes
     *      {@link #onRootLayoutChanged(int)}</li>
     * </ul>
     */
    private View mActivityRootView;
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;

    /**
     * Create a new Global Layout Listener
     */
    private ViewTreeObserver.OnGlobalLayoutListener createLayoutListener() {
        Log.d(TAG, "createLayoutListener");
        final int KEYBOARD_GREATER_THAN = 125;
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();

                mActivityRootView.getWindowVisibleDisplayFrame(rect);

                int heightDiff = mActivityRootView.getHeight() - (rect.bottom - rect.top);
                if (heightDiff>0) onRootLayoutChanged(heightDiff);
                if (heightDiff > KEYBOARD_GREATER_THAN) { // if more than 100 pixels, its probably a keyboard...
                    Log.d(TAG, "onGlobalLayout(). Probabaly a keyBoard. > heightDiff[" + heightDiff + "]");
                    onKeyBoardOn();
                    mIsKeyboardOn = true;
                } else {
                    if (mIsKeyboardOn) {
                        onKeyBoardOff();
                        mIsKeyboardOn = false;
                    }
                }
            }
        };
    }

    /**
     * Start to detect changed on Root layout height
     * - Register a Root view
     * - Create and starts a Global Layout Listener
     * - Register root view layout events
     *      {@link #onKeyBoardOn()}
     *      {@link #onKeyBoardOff()}
     *      {@link #onRootLayoutChanged(int)}
     * @param activityRootView  Root layout to be monitored
     */
    protected void startDetectRootViewChanges(final View activityRootView) {
        Log.d(TAG, "startDetectRootViewChanges()");
        mActivityRootView = activityRootView;
        mIsKeyboardOn = false;

        mLayoutListener = createLayoutListener();
        mActivityRootView.getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

    }

    /**
     * Remove Global Layout change listener
     */
    protected void stopDetectRootViewChanges() {
        Log.d(TAG, "stopDetectRootViewChanges()");
        if ( mActivityRootView != null )
            mActivityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutListener);
    }


    private Boolean mIsKeyboardOn;
    /**
     * Controls if SoftKeyBoard is on/off
     * - May return null if {@link #startDetectRootViewChanges(View)} wasn't called
     * @return true if SoftKeyboard is showing on screen
     */
    protected Boolean isKeyboardOn() {
        Log.d(TAG, "isKeyboardOn()");
        return mIsKeyboardOn;
    }

    /**
     * Called on SoftKeyBoard is ON
     * - Depends on {@link #startDetectRootViewChanges(View)}
     * - It must be called more than one time
     */
    protected void onKeyBoardOn() {
        Log.d(TAG, "onKeyBoardOn()");
    }
    /**
     * Called on SoftKeyBoard is OFF
     * - Depends on {@link #startDetectRootViewChanges(View)}
     * - It must be called more than one time
     */
    protected synchronized void onKeyBoardOff() {
        Log.d(TAG, "onKeyBoardOff()");
    }
    /**
     * Called when Root Layout view changes
     * - Depends on {@link #startDetectRootViewChanges(View)}
     * @param heightDiff    Height Difference between current
     *                      Root View an available Windo size
     */
    protected void onRootLayoutChanged(int heightDiff) {
        Log.d(TAG, "onRootLayoutChanged(heightDiff[" + heightDiff + "])");
    }
}
