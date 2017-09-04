package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.R;
import sample.kingja.loadsir.Util;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ActivityTargetActivity extends AppCompatActivity {

    private LoadSir loadSir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        loadSir = LoadSir.call(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // Your can change the status out of Main thread.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadSir.showWithStatus(LoadingCallback.class);
                        SystemClock.sleep(500);
                        loadSir.showWithStatus(SuccessCallback.class);
                    }
                }).start();

            }
        });
        Util.goLoadCallback(loadSir,EmptyCallback.class);
    }

}
