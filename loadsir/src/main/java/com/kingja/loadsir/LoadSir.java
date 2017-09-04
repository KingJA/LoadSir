package com.kingja.loadsir;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.DefaultCallback;
import com.kingja.loadsir.callback.LoadCallback;

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

    public LoadSir(Builder builder) {
        LoadSir.builder = builder;
    }

    private LoadSir(Object target, LoadCallback.OnReloadListener onReloadListener) {
        ViewGroup contentParent;
        Context context;
        if (target instanceof Activity) {
            Activity activity = (Activity) target;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (target instanceof Fragment) {
            Fragment fragment = (Fragment) target;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (target instanceof View) {
            View view = (View) target;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
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
        loadLayout.addLoadCallback(DefaultCallback.createContentCallback(oldContent, context, onReloadListener));
        addLoadCallbacks(builder);
        showLoadCallback(DefaultCallback.LOADING_CALLBACK);

    }

    public static Builder getBuilder() {
        return builder;
    }

    private void addLoadCallbacks(Builder builder) {
        for (LoadCallback loadCallback : builder.loadCallbacks) {
            loadLayout.addCustomLoadCallback(loadCallback);
        }
    }

    public static LoadSir callLoadSir(Object target, LoadCallback.OnReloadListener onReloadListener) {
        return new LoadSir(target, onReloadListener);
    }

    public void showLoadCallback(int status) {
        loadLayout.showStatus(status);
    }

    public static class Builder {
        private List<LoadCallback> loadCallbacks = new ArrayList<>();
        private int errorLayout = R.layout.layout_error;
        private int loadingLayout = R.layout.layout_loading;
        private int emptyLayout = R.layout.layout_empty;

        public LoadSir build() {
            return new LoadSir(this);
        }

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

        public Builder add(LoadCallback callback) {
            loadCallbacks.add(callback);
            return this;
        }

        public Builder add(List<LoadCallback> callbacks) {
            loadCallbacks.addAll(callbacks);
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
    }

}
