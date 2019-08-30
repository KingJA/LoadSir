package sample.kingja.loadsir.target;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;

import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.callback.EmptyCallback;
import sample.kingja.loadsir.callback.LoadingCallback;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ConstraintLayoutActivity extends AppCompatActivity {

    private static final String TAG = "ConstraintLayout";
    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout);
        (findViewById(R.id.tv1)).getParent();
        Log.e(TAG, "tv1: "+(findViewById(R.id.tv1).getLayoutParams() instanceof ConstraintLayout.LayoutParams) );
        Log.e(TAG, "banner: "+(findViewById(R.id.banner).getLayoutParams() instanceof ConstraintLayout.LayoutParams));
        Log.e(TAG, "tv0: "+(findViewById(R.id.tv0).getLayoutParams() instanceof ConstraintLayout.LayoutParams));
        Log.e(TAG, "tv2: "+(findViewById(R.id.tv2).getLayoutParams() instanceof ConstraintLayout.LayoutParams) );
        // Your can change the callback on sub thread directly.
        loadService = LoadSir.getDefault().register(findViewById(R.id.banner), new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // Your can change the status out of Main thread.
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadService.showCallback(LoadingCallback.class);
                        //do retry logic...
                        SystemClock.sleep(500);
                        //callback
                        loadService.showSuccess();
                    }
                }).start();
            }
        }).setCallBack(EmptyCallback.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                TextView mTvEmpty = (TextView) view.findViewById(R.id.tv_empty);
                mTvEmpty.setText("fine, no data. You must fill it!");
            }
        });
        PostUtil.postCallbackDelayed(loadService, EmptyCallback.class);
    }

}
