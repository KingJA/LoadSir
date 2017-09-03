package com.kingja.loadsir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.kingja.loadsir.callback.DefaultCallback;
import com.kingja.loadsir.callback.LoadCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/3.
 */

public class LoadLayout extends FrameLayout {
    private Map<Integer, LoadCallback> callbacks = new HashMap<>();
    private Context context;
    private LoadCallback.OnReloadListener onReloadListener;

    public LoadLayout(@NonNull Context context, LoadCallback.OnReloadListener onReloadListener) {
        super(context);
        this.context = context;
        this.onReloadListener = onReloadListener;
        addDefaultLoadCallback(DefaultCallback.createErrorCallback(null, context, onReloadListener));
        addDefaultLoadCallback(DefaultCallback.createLoadingCallback(null, context, onReloadListener));
    }


    public void addDefaultLoadCallback(LoadCallback loadCallback) {
        addView(loadCallback.getRootView());
        callbacks.put(loadCallback.getStatus(), loadCallback);
    }

    public void addLoadCallback(LoadCallback loadCallback) {
        loadCallback.setCallback(null, context, onReloadListener);
        addView(loadCallback.getRootView());
        callbacks.put(loadCallback.getStatus(), loadCallback);
    }

    public void showStatus(int status) {
        for (Integer key : callbacks.keySet()) {
            LoadCallback loadCallback = callbacks.get(key);
            if (key == status) {
                loadCallback.show();
            } else {
                loadCallback.hide();
            }
        }
    }
}
