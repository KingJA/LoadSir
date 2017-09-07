package sample.kingja.loadsir;

import android.os.Handler;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadService;

/**
 * Description:TODO
 * Create Time:2017/9/4 15:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PostUtil {
    public static final int DELAY_TIME=500;

    public static void postCallbackDelayed(final LoadService loadService, final Class<? extends Callback> clazz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showCallback(clazz);
            }
        }, DELAY_TIME);
    } public static void postSuccessDelayed(final LoadService loadService) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showCallback(SuccessCallback.class);
            }
        }, DELAY_TIME);
    }
}
