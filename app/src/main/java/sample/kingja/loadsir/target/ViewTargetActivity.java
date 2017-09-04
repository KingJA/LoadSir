package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.R;
import sample.kingja.loadsir.Util;


/**
 * Description:TODO
 * Create Time:2017/9/3 11:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ViewTargetActivity extends AppCompatActivity {

    private LoadSir loadSir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ImageView iv_img = (ImageView) findViewById(R.id.iv_img);
        loadSir = LoadSir.call(iv_img, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                Util.goLoadCallback(loadSir,SuccessCallback.class);
            }
        });
        Util.goLoadCallback(loadSir,ErrorCallback.class);
    }

}
