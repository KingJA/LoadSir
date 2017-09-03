package sample.kingja.loadsir;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.LoadCallback;

/**
 * Created by Administrator on 2017/9/3.
 */

public class CustomCallback extends LoadCallback {
    public static final int CUSTOM_CALLBACK=0x03;

    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }

    @Override
    public int getStatus() {
        return CUSTOM_CALLBACK;
    }
}
