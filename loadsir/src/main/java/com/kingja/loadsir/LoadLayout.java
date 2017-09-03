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

    public LoadLayout(@NonNull Context context, LoadCallback.OnReloadListener onReloadListener) {
        super(context);
        addLoadCallback(DefaultCallback.createErrorCallback(null,context,onReloadListener));
        addLoadCallback(DefaultCallback.createLoadingCallback(null,context,onReloadListener));
    }


    public void addLoadCallback(LoadCallback loadCallback) {
        addView(loadCallback.getRootView());
        callbacks.put(loadCallback.getStatus(),loadCallback);
    }

    public void showStatus(int status) {
        for (Integer key : callbacks.keySet()) {
            LoadCallback loadCallback = callbacks.get(key);
            if (key == status) {
                loadCallback.show();
            }else{
                loadCallback.hide();
            }
        }
    }
}
