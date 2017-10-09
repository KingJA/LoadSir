package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.LoadSirHintCallback;
import com.kingja.loadsir.callback.LoadSirLoadingCallback;
import com.kingja.loadsir.callback.OnRetryListener;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class DefaultCallbackActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        // Your can change the callback on sub thread directly.
        LoadSirLoadingCallback loadingCallback = new LoadSirLoadingCallback.Builder()
                .setTitle("loading")
                .setSubTitle("Don't worry, I will be back soon.")
                .build();

        LoadSirHintCallback hintCallback = new LoadSirHintCallback.Builder()
                .setTitle("error")
                .setSubTitle("Sorry, boss, I will try it again.")
                .setHintImg(R.drawable.awkward)
                .setRetry("retry", new OnRetryListener() {
                    @Override
                    public void onRetry() {

                    }
                })
                .build();

        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(loadingCallback)
                .addCallback(hintCallback)
                .setDefaultCallback(LoadSirLoadingCallback.class)
                .build();

        LoadService loadService = loadSir.register(this, null);
        PostUtil.postCallbackDelayed(loadService, LoadSirHintCallback.class);
    }

}
