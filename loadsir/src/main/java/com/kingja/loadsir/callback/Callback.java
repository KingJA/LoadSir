package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Description:TODO
 * Create Time:2017/9/2 17:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class Callback implements Serializable {
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
        if (resId == 0 && rootView != null) {
            return rootView;
        }
        rootView = View.inflate(context, onCreateView(), null);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRetry(context, rootView)) {
                    return;
                }
                if (onReloadListener != null) {
                    onReloadListener.onReload(v);
                }
            }
        });
        return rootView;
    }

    protected boolean onRetry(Context context, View view) {
        return false;
    }

    public Callback copy() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois;
        Object obj = null;
        try {
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Callback) obj;
    }

    public interface OnReloadListener {
        void onReload(View v);
    }

    protected abstract int onCreateView();
}
