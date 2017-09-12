package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.callback.PlaceholderCallback;


/**
 * Description:TODO
 * Create Time:2017/9/3 11:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class PlaceholderActivity extends AppCompatActivity {

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new PlaceholderCallback())
                .setDefaultCallback(PlaceholderCallback.class)
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                //do retry logic...
            }
        });
        PostUtil.postSuccessDelayed(loadService, 1000);
    }

}
