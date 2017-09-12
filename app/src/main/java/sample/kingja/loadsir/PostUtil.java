package sample.kingja.loadsir;

import android.os.Handler;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;

/**
 * Description:TODO
 * Create Time:2017/9/4 15:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PostUtil {
    public static final int DELAY_TIME = 1000;

    public static void postCallbackDelayed(final LoadService loadService, final Class<? extends Callback> clazz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showCallback(clazz);
            }
        }, DELAY_TIME);
    }

    public static void postSuccessDelayed(final LoadService loadService) {
        postSuccessDelayed(loadService, DELAY_TIME);
    }

    public static void postSuccessDelayed(final LoadService loadService, long delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showSuccess();
            }
        }, delay);
    }
}
