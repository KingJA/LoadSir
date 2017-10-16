package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.HintCallback;
import com.kingja.loadsir.callback.ProgressCallback;
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

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        ProgressCallback loadingCallback = new ProgressCallback.Builder()
                .setTitle("Loading", R.style.Hint_Title)
//                .setAboveSuccess(true)// attach loadingView above successView
                .build();

        HintCallback hintCallback = new HintCallback.Builder()
                .setTitle("Error", R.style.Hint_Title)
                .setSubTitle("Sorry, buddy, I will try it again.")
                .setHintImg(R.drawable.error)
                .build();

        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(loadingCallback)
                .addCallback(hintCallback)
                .setDefaultCallback(ProgressCallback.class)
                .build();

        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(ProgressCallback.class);
                PostUtil.postSuccessDelayed(loadService);

            }
        });
        PostUtil.postCallbackDelayed(loadService, HintCallback.class);
    }

}
