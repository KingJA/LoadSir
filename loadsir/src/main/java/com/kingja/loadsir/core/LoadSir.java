package com.kingja.loadsir.core;

import com.kingja.loadsir.Util;
import com.kingja.loadsir.callback.Callback;
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
    private static volatile LoadSir loadSir;
    private final Builder builder;

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

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        TargetContext targetContext = Util.getTargetContext(target);
        return new LoadService(convertor, targetContext, onReloadListener, builder);
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private Class<? extends Callback> initializeCallback;
        private boolean addDefault = true;


        public Builder setInitializeCallback(Class<? extends Callback> initializeCallback) {
            this.initializeCallback = initializeCallback;
            return this;
        }

        public Builder addCallback(Callback callback) {
            callbacks.add(callback);
            return this;
        }

        public Builder setDefaultable(boolean addDefault) {
            this.addDefault = addDefault;
            return this;
        }

        public boolean getAddDefault() {
            return addDefault;
        }

        public List<Callback> getCallbacks() {
            return callbacks;
        }

        public Class<? extends Callback> getInitializeCallback() {
            return initializeCallback;
        }

        public LoadSir build() {
            return new LoadSir(this);
        }

    }

}
