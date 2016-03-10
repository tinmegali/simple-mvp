package com.tinmegali.mvp;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

/**
 * ---------------------------------------------------
 * Created by Tin Megali on 24/02/16.
 * Project: AndroidMVP
 * ---------------------------------------------------
 * <a href="http://www.tinmegali.com">tinmegali.com</a>
 * <a href="http://www.github.com/tinmegali">github</a>
 * ---------------------------------------------------
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
     * Exibe uma mensagem toast com tempo curto
     */
    protected void showToast(String msg) {
        Toast.makeText(
                getApplicationContext(), msg, Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Exibe uma mensagem toast com tempo curto
     * @param duration Duração do tosat.
     *                      {@link Toast#LENGTH_LONG}
     *                      {@link Toast#LENGTH_SHORT}
     */
    protected void showToast(String msg, final int duration) {
        Toast.makeText(
                getApplicationContext(), msg, Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Exibe Snackbar
     * @param view  View pai
     */
    protected void showSnackbar(String msg, View view){
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }


    /**
     * Exibe Snackbar com tempo personalizado
     * @param view  View pai
     * @param duration  duração do tipo:
     *                      {@link Snackbar#LENGTH_SHORT}
     *                      {@link Snackbar#LENGTH_LONG}
     *                      {@link Snackbar#LENGTH_INDEFINITE}
     */
    protected void showSnackbar(String msg, View view, int duration){
        Snackbar.make( view, msg, duration).show();
    }


    /**
     * Exibe Snackbar recebida
     */
    protected void showSnackbar(Snackbar snackbar){
        snackbar.show();
    }


    /**
     * Detecting Layout Root changes
     * - {@link #startDetectRootViewChanges(View)} must be called on {@link #onCreate(Bundle)}
     * - Detect if SoftKeyboard is On/Off
     *      {@link #onKeyBoardOn()} {@link #onKeyBoardOff()}
     * - Register event for layout changes
     *      {@link #onRootLayoutChanged(int)}
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
