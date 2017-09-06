package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.convertor.Convertor;

import java.util.Random;

import sample.kingja.loadsir.R;
import sample.kingja.loadsir.PostUtil;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ConvertorActivity extends AppCompatActivity {

    private LoadService loadService;
    private Result mResult = new Result(new Random().nextInt(3));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_convertor);
        LoadSir loadSir = new LoadSir.Builder().setInitializeCallback(LoadingCallback.class).build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                PostUtil.postCallbackDelayed(loadService, SuccessCallback.class);
            }
        }, new Convertor<Result>() {
            @Override
            public Class<? extends Callback> change2Callback(Result result) {
                Class<? extends Callback> resultCode = SuccessCallback.class;
                switch (result.getResultCode()) {
                    case 0:
                        resultCode = SuccessCallback.class;
                        break;
                    case 1:
                        resultCode = EmptyCallback.class;
                        break;
                    case 2:
                        resultCode = ErrorCallback.class;
                        break;
                }
                return resultCode;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showWithStatus(LoadingCallback.class);
                //do retry logic...

                //callback
                loadService.showWithConvertor(mResult);
            }
        }, 500);
    }

    class Result {
        Result(int resultCode) {
            this.resultCode = resultCode;
        }

        private int resultCode;

        int getResultCode() {
            return resultCode;
        }

    }
}
