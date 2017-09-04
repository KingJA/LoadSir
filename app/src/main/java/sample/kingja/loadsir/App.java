package sample.kingja.loadsir;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;
import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        new LoadSir.Builder()
                .add(new CustomCallback())
                .build();
    }
}
