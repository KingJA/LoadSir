package sample.kingja.loadsir;

import android.os.Handler;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.core.LoadSir;

/**
 * Description:TODO
 * Create Time:2017/9/4 15:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Util {
    public static final int DELAY_TIME=500;

    public static void goLoadCallback(final LoadSir loadSir, final Class<? extends Callback> clazz) {
        loadSir.showWithStatus(LoadingCallback.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSir.showWithStatus(clazz);
            }
        }, DELAY_TIME);
    }
}
