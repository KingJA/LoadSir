package com.kingja.loadsir.core;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.LoadingCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/9/2 17:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

class LoadLayout extends FrameLayout {
    private Map<Class<? extends Callback>, Callback> callbacks = new HashMap<>();
    private Context context;
    private Callback.OnReloadListener onReloadListener;

    public LoadLayout(@NonNull Context context) {
        super(context);
    }

    public LoadLayout(@NonNull Context context, Callback.OnReloadListener onReloadListener) {
        this(context);
        this.context = context;
        this.onReloadListener = onReloadListener;
        addCustomLoadCallback(new ErrorCallback());
        addCustomLoadCallback(new EmptyCallback());
        addCustomLoadCallback(new LoadingCallback());
    }

    public void addCustomLoadCallback(Callback callback) {
        callback.setCallback(null, context, onReloadListener);
        addLoadCallback(callback);
    }

    public void addLoadCallback(Callback callback) {
        addView(callback.getRootView());
        callbacks.put(callback.getClass(), callback);
    }

    public void showStatus(final Class<? extends Callback> status) {
        if (!callbacks.containsKey(status)) {
            throw new IllegalArgumentException(String.format("The Callback (%s) is nonexistent.", status
                    .getSimpleName()));
        }
        if (isMainThread()) {
            setCallbackVisibility(status);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    setCallbackVisibility(status);
                }
            });
        }
    }
    private void setCallbackVisibility(Class<? extends Callback> status) {
        for (Class key : callbacks.keySet()) {
            Callback callback = callbacks.get(key);
            if (key == status) {
                callback.show();
            } else {
                callback.hide();
            }
        }
    }

    private boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
