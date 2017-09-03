package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.LoadSir;
import com.kingja.loadsir.R;

/**
 * Created by Administrator on 2017/9/3.
 */

public class DefaultCallback {
    public static final int CONTENT_CALLBACK = 0;
    public static final int LOADING_CALLBACK = 1;
    public static final int ERROR_CALLBACK = 2;
    public static final int EMPTY_CALLBACK = 3;

    public static LoadCallback createErrorCallback(View view, Context context, LoadCallback.OnReloadListener onReloadListener) {

        return new LoadCallback(view, context, onReloadListener) {

            @Override
            protected int onCreateView() {
                return LoadSir.getBuilder().getErrorLayout();
            }

            @Override
            public int getStatus() {
                return ERROR_CALLBACK;
            }
        };
    }

    public static LoadCallback createLoadingCallback(View view, Context context, LoadCallback.OnReloadListener onReloadListener) {
        return new LoadCallback(view, context, onReloadListener) {

            @Override
            protected int onCreateView() {
                return LoadSir.getBuilder().getLoadingLayout();
            }

            @Override
            public int getStatus() {
                return LOADING_CALLBACK;
            }
        };
    }

    public static LoadCallback createEmptyCallback(View view, Context context, LoadCallback.OnReloadListener onReloadListener) {
        return new LoadCallback(view, context, onReloadListener) {

            @Override
            protected int onCreateView() {
                return LoadSir.getBuilder().getEmptyLayout();
            }

            @Override
            public int getStatus() {
                return EMPTY_CALLBACK;
            }
        };
    }

    public static LoadCallback createContentCallback(View view, Context context, LoadCallback.OnReloadListener onReloadListener) {
        return new LoadCallback(view, context, onReloadListener) {

            @Override
            protected int onCreateView() {
                return 0;
            }

            @Override
            public int getStatus() {
                return CONTENT_CALLBACK;
            }
        };
    }
}
