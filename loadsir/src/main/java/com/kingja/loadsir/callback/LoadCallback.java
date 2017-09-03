package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;


/**
 * Description:TODO
 * Create Time:2017/9/2 17:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class LoadCallback {
    protected View rootView;
    private Context context;
    private OnReloadListener onReloadListener;


    public LoadCallback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }


    public View getRootView() {
        View view = onCreateView(context);
        if (view != null) {
            this.rootView = view;
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadListener != null) {
                    onReloadListener.onReload(v);
                }
            }
        });
        return rootView;
    }

    protected abstract View onCreateView(Context context);


    public abstract int getStatus();

    public void hide() {
        rootView.setVisibility(View.GONE);
    }

    public void show() {
        rootView.setVisibility(View.VISIBLE);
    }

    public interface OnReloadListener {
        void onReload(View v);
    }

}
