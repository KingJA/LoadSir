package sample.kingja.loadsir;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.callback.CustomCallback;
import sample.kingja.loadsir.callback.EmptyCallback;
import sample.kingja.loadsir.callback.ErrorCallback;
import sample.kingja.loadsir.callback.LoadingCallback;
import sample.kingja.loadsir.callback.TimeoutCallback;

/**
 * Description:TODO
 * Create Time:2017/9/3 14:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
