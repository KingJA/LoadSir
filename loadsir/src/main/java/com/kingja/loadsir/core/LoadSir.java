package com.kingja.loadsir.core;


import com.kingja.loadsir.LoadSirUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.target.ActivityTarget;
import com.kingja.loadsir.target.ITarget;
import com.kingja.loadsir.target.ViewTarget;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSir {
    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    private void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(@NonNull Object target) {
        return register(target, null, null);
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        ITarget targetContext = LoadSirUtil.getTargetContext(target, builder.getTargetContextList());
        LoadLayout loadLayout = targetContext.replaceView(target, onReloadListener);
        return new LoadService<>(convertor,loadLayout,  builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private List<ITarget> targetContextList = new ArrayList<>();
        private Class<? extends Callback> defaultCallback;

        {
            targetContextList.add(new ActivityTarget());
            targetContextList.add(new ViewTarget());
        }

        public Builder addCallback(@NonNull Callback callback) {
            callbacks.add(callback);
            return this;
        }

        /**
         * @param targetContext
         * @return Builder
         * @since 1.3.8
         */
        public Builder addTargetContext(ITarget targetContext) {
            targetContextList.add(targetContext);
            return this;
        }

        public List<ITarget> getTargetContextList() {
            return targetContextList;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends Callback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<Callback> getCallbacks() {
            return callbacks;
        }

        Class<? extends Callback> getDefaultCallback() {
            return defaultCallback;
        }

        public void commit() {
            getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }

    }
}
