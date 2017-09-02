package com.kingja.loadsir;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSir {
    private Object target;
    private OnReloadListener onReloadListener;

    private LoadSir(Object target, OnReloadListener onReloadListener) {
        this.target = target;
        this.onReloadListener = onReloadListener;
    }

    public static LoadSir callLoadSir(Object target, OnReloadListener onReloadListener) {
        return new LoadSir(target, onReloadListener);
    }

    interface OnReloadListener {
        void onReload();
    }

}
