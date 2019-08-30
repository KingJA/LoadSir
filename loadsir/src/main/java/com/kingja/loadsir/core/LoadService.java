package com.kingja.loadsir.core;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/6 10:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadService<T> {
    private static final String TAG = "LoadService";
    private LoadLayout loadLayout;
    private Convertor<T> convertor;

    LoadService(Convertor<T> convertor, TargetContext targetContext, Callback.OnReloadListener onReloadListener,
                LoadSir.Builder builder) {
        this.convertor = convertor;
        Context context = targetContext.getContext();
        View oldContent = targetContext.getOldContent();

        loadLayout = new LoadLayout(context, onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, context,
                onReloadListener));
        if (targetContext.getParentView() != null) {
            //兼容1.3.8版本前的Fragmeng注册方式
            Log.e(TAG, "LoadService:addView ");
            Log.e(TAG, "loadLayout:getLayoutParams " + loadLayout.getLayoutParams());
            if (targetContext.getParentView() instanceof ConstraintLayout) {
                Log.e(TAG, "LoadService:addView ConstraintLayout");
                targetContext.getParentView().addView(loadLayout, targetContext.getLayoutParams());
            } else {
                targetContext.getParentView().addView(loadLayout, targetContext.getChildIndex(),
                        targetContext.getLayoutParams());
            }

            Log.e(TAG, "加入完毕 ");
        }
        initCallback(builder);
    }

    private void initCallback(LoadSir.Builder builder) {
        Log.e(TAG, "initCallback ");
        List<Callback> callbacks = builder.getCallbacks();
        Class<? extends Callback> defalutCallback = builder.getDefaultCallback();
        if (callbacks != null && callbacks.size() > 0) {
            for (Callback callback : callbacks) {
                loadLayout.setupCallback(callback);
            }
        }
        if (defalutCallback != null) {
            loadLayout.showCallback(defalutCallback);
        }
        Log.e(TAG, "initCallback 完毕");
    }

    public void showSuccess() {
        loadLayout.showCallback(SuccessCallback.class);
    }

    public void showCallback(Class<? extends Callback> callback) {
        Log.e(TAG, "showCallback :" + callback.getSimpleName());
        loadLayout.showCallback(callback);
    }

    public void showWithConvertor(T t) {
        if (convertor == null) {
            throw new IllegalArgumentException("You haven't set the Convertor.");
        }
        loadLayout.showCallback(convertor.map(t));
    }

    public LoadLayout getLoadLayout() {
        return loadLayout;
    }

    public Class<? extends Callback> getCurrentCallback() {
        return loadLayout.getCurrentCallback();
    }

    /**
     * obtain rootView if you want keep the toolbar in Fragment
     *
     * @since 1.2.2
     * @deprecated
     */
    public LinearLayout getTitleLoadLayout(Context context, ViewGroup rootView, View titleView) {
        LinearLayout newRootView = new LinearLayout(context);
        newRootView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        newRootView.setLayoutParams(layoutParams);
        rootView.removeView(titleView);
        newRootView.addView(titleView);
        newRootView.addView(loadLayout, layoutParams);
        return newRootView;
    }

    /**
     * modify the callback dynamically
     *
     * @param callback  which callback you want modify(layout, event)
     * @param transport a interface include modify logic
     * @since 1.2.2
     */
    public LoadService<T> setCallBack(Class<? extends Callback> callback, Transport transport) {
        loadLayout.setCallBack(callback, transport);
        return this;
    }
}
