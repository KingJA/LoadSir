package com.kingja.loadsir.core;

import android.os.Build;
import android.view.ViewGroup;

import com.kingja.loadsir.R;
import com.kingja.loadsir.Util;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.convertor.Convertor;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSir {
    private LoadLayout mLoadLayout;
    private  Builder builder = new Builder();
    private Convertor convertor;

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    private <T> LoadSir(Object target, Callback.OnReloadListener onReloadListener, Convertor<T> convertor) {
        this.convertor = convertor;
        TargetContext targetContext = Util.getTargetContext(target);
        mLoadLayout = new LoadLayout(targetContext.getContext(), onReloadListener);
        ViewGroup.LayoutParams lp = targetContext.getOldContent().getLayoutParams();
        if (targetContext.getParentView() != null) {
            targetContext.getParentView().addView(mLoadLayout, targetContext.getChildIndex(), lp);
        }
        mLoadLayout.addCallback(new SuccessCallback(targetContext.getOldContent(), targetContext.getContext(),
                onReloadListener));
        initLoadCallback();

    }

    private void initLoadCallback() {
        addLoadCallbacks(builder.callbacks);
        setInitializeCallback(builder.initializeCallback);
    }

    public void setInitializeCallback(Class<? extends Callback> initializeCallback) {
        mLoadLayout.showStatus(initializeCallback);
    }

    public  Builder getBuilder() {
        return builder;
    }

    private void addLoadCallbacks(List<Callback> callbacks) {
        for (Callback callback : callbacks) {
            mLoadLayout.setupCallback(callback);
        }
    }

    public static LoadSir call(Object target, Callback.OnReloadListener onReloadListener) {
        return new LoadSir(target, onReloadListener, null);
    }

    public static <T> LoadSir call(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        return new LoadSir(target, onReloadListener, convertor);
    }

    public void showWithStatus(Class<? extends Callback> status) {
        mLoadLayout.showStatus(status);
    }

    public <T> void showWithConvertor(T t) {
        if (convertor != null) {
            mLoadLayout.showStatus(convertor.change2Callback(t));
        } else {
            throw new IllegalArgumentException("You haven't set the Convertor.");
        }
    }

    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    public void setLoadLayout(LoadLayout loadLayout ) {
        this.mLoadLayout = loadLayout;
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private int errorLayout = R.layout.layout_error;
        private int loadingLayout = R.layout.layout_loading;
        private int emptyLayout = R.layout.layout_empty;
        private Class<? extends Callback> initializeCallback = LoadingCallback.class;
        private boolean addDefault = true;

        public Builder setErrorLayout(int errorLayout) {
            this.errorLayout = errorLayout;
            return this;
        }

        public Builder setLoadingLayout(int loadingLayout) {
            this.loadingLayout = loadingLayout;
            return this;
        }

        public Builder setEmptyLayout(int emptyLayout) {
            this.emptyLayout = emptyLayout;
            return this;
        }

        public Builder addDefaultLayout(boolean addDefault) {
            this.addDefault = addDefault;
            return this;
        }

        public int getErrorLayout() {
            return errorLayout;
        }

        public int getLoadingLayout() {
            return loadingLayout;
        }

        public int getEmptyLayout() {
            return emptyLayout;
        }

        public Builder setInitializeCallback(Class<? extends Callback> initializeCallback) {
            this.initializeCallback = initializeCallback;
            return this;
        }

        public Builder add(Callback callback) {
            callbacks.add(callback);
            return this;
        }

        public Builder add(List<Callback> callbacks) {
            this.callbacks.addAll(callbacks);
            return this;
        }

        public LoadSir build() {
            if (addDefault) {
                addDefaultCallback();
            }
            return new LoadSir(this);
        }

        private void addDefaultCallback() {
            callbacks.add(new ErrorCallback());
            callbacks.add(new EmptyCallback());
            callbacks.add(new LoadingCallback());
        }
    }

}
