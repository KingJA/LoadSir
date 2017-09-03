package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.R;

/**
 * Created by Administrator on 2017/9/3.
 */

public class DefaultCallback {
    public static final int CONTENT_CALLBACK = 0x00;
    public static final int LOADING_CALLBACK = 0x01;
    public static final int ERROR_CALLBACK = 0x02;

    public static LoadCallback createErrorCallback(View view, Context context, LoadCallback.OnReloadListener onReloadListener) {

        return new LoadCallback(view, context, onReloadListener) {

            @Override
            protected int onCreateView() {
                return R.layout.layout_error;
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
                return R.layout.layout_loading;
            }

            @Override
            public int getStatus() {
                return LOADING_CALLBACK;
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
