package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.convertor.Convertor;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/6 10:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadService {
    private LoadLayout loadLayout;
    private Convertor convertor;

    public LoadService(Convertor convertor, TargetContext targetContext, Callback
            .OnReloadListener onReloadListener, LoadSir.Builder builder) {
        this.convertor = convertor;
        Context context = targetContext.getContext();
        View oldContent = targetContext.getOldContent();
        loadLayout = new LoadLayout(context, onReloadListener);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        if (targetContext.getParentView() != null) {
            targetContext.getParentView().addView(loadLayout, targetContext.getChildIndex(), lp);
        }
        loadLayout.addCallback(new SuccessCallback(oldContent, context,
                onReloadListener));
        initLoadCallback(builder);
    }


    private void initLoadCallback(LoadSir.Builder builder) {
        boolean addDefault = builder.getAddDefault();
        List<Callback> callbacks =builder.getCallbacks();
        Class<? extends Callback> initializeCallback = builder.getInitializeCallback();

        if (addDefault) {
            loadLayout.setupCallback(new ErrorCallback());
            loadLayout.setupCallback(new EmptyCallback());
            loadLayout.setupCallback(new LoadingCallback());
        }

        if (callbacks != null && callbacks.size() > 0) {
            for (Callback callback : callbacks) {
                loadLayout.setupCallback(callback);
            }
        }
        if (initializeCallback != null) {
            loadLayout.showStatus(initializeCallback);
        }
    }

    public void showWithStatus(Class<? extends Callback> status) {
        loadLayout.showStatus(status);
    }

    public <T> void showWithConvertor(T t) {
        if (convertor != null) {
            loadLayout.showStatus(convertor.change2Callback(t));
        } else {
            throw new IllegalArgumentException("You haven't set the Convertor.");
        }
    }

    public LoadLayout getLoadLayout() {
        return loadLayout;
    }
}
