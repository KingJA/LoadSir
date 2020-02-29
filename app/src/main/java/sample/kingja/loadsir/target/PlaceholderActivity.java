package sample.kingja.loadsir.target;

import android.os.Bundle;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeholder);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new PlaceholderCallback())
                .setDefaultCallback(PlaceholderCallback.class)
                .build();
        //do retry logic...
        LoadService loadService = loadSir.register(this, (Callback.OnReloadListener) v -> {
            //do retry logic...
        });
        PostUtil.postSuccessDelayed(loadService, 1000);
    }

}
