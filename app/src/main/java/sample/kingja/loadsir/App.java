package sample.kingja.loadsir;

import android.app.Application;

import com.kingja.loadsir.LoadSir;

/**
 * Created by Administrator on 2017/9/3.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new LoadSir.Builder().add(new CustomCallback()).build();
    }
}
