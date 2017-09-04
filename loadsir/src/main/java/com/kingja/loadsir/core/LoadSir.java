package com.kingja.loadsir.core;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.R;
import com.kingja.loadsir.callback.Callback;
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
    private LoadLayout loadLayout;
    private static Builder builder = new Builder();
    private Convertor convertor;

    private LoadSir(Builder builder) {
        LoadSir.builder = builder;
    }

    private <T> LoadSir(Object target, Callback.OnReloadListener onReloadListener, Convertor<T> convertor) {
        this.convertor = convertor;
        ViewGroup contentParent;
        Context context;
        if (target instanceof Activity) {
            Activity activity = (Activity) target;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (target instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) target;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (target instanceof android.app.Fragment) {
            android.app.Fragment fragment = (android.app.Fragment) target;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (target instanceof View) {
            View view = (View) target;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("The target must be within Activity, Fragment, View.");
        }
        int childCount = contentParent.getChildCount();
        //get contentParent
        int index = 0;
        View oldContent;
        if (target instanceof View) {
            oldContent = (View) target;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    index = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent.getChildAt(0);
        }
        contentParent.removeView(oldContent);
        //setup content layout
        loadLayout = new LoadLayout(context, onReloadListener);
        ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
        contentParent.addView(loadLayout, index, lp);
        loadLayout.addLoadCallback(new SuccessCallback(oldContent, context, onReloadListener));
        addLoadCallbacks(builder);
        showWithStatus(LoadingCallback.class);

    }

    public static Builder getBuilder() {
        return builder;
    }

    private void addLoadCallbacks(Builder builder) {
        for (Callback callback : builder.callbacks) {
            loadLayout.addCustomLoadCallback(callback);
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
        loadLayout.showStatus(status);
    }

    public <T> void showWithConvertor(T t) {
        if (convertor != null) {
            loadLayout.showStatus(convertor.change2Callback(t));
        }
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private int errorLayout = R.layout.layout_error;
        private int loadingLayout = R.layout.layout_loading;
        private int emptyLayout = R.layout.layout_empty;

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

        public int getErrorLayout() {
            return errorLayout;
        }

        public int getLoadingLayout() {
            return loadingLayout;
        }

        public int getEmptyLayout() {
            return emptyLayout;
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
            return new LoadSir(this);
        }
    }


}
