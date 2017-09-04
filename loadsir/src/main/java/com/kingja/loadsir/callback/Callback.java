package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;


/**
 * Description:TODO
 * Create Time:2017/9/2 17:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class Callback {
    private View rootView;
    private Context context;
    private OnReloadListener onReloadListener;

    public Callback() {
    }

    Callback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public Callback setCallback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
        return this;
    }

    public View getRootView() {
        int resId = onCreateView();
        if (resId == 0) {
            return rootView;
        }
        rootView = View.inflate(context, onCreateView(), null);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSingleRetry(context, rootView)) {
                    return;
                }
                if (onReloadListener != null) {
                    onReloadListener.onReload(v);
                }
            }
        });
        return rootView;
    }

    public void hide() {
        rootView.setVisibility(View.GONE);
    }

    public void show() {
        rootView.setVisibility(View.VISIBLE);
    }

    public interface OnReloadListener {
        void onReload(View v);
    }

    protected abstract int onCreateView();

    protected boolean onSingleRetry(Context context, View view) {
        return false;
    }

}
