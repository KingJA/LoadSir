package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sample.kingja.loadsir.callback.EmptyCallback;
import sample.kingja.loadsir.callback.ErrorCallback;
import sample.kingja.loadsir.callback.LoadingCallback;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import com.kingja.loadsir.core.Convertor;

import java.util.ArrayList;
import java.util.List;
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
    private HttpResult mHttpResult = new HttpResult(new Random().nextInt(2), new ArrayList<>());
    private static final int SUCCESS_CODE = 0x00;
    private static final int ERROR_CODE = 0x01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_convertor);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new LoadingCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                PostUtil.postCallbackDelayed(loadService, SuccessCallback.class);
            }
        }, new Convertor<HttpResult>() {
            @Override
            public Class<? extends Callback> map(HttpResult httpResult) {
                Class<? extends Callback> resultCode = SuccessCallback.class;
                switch (httpResult.getResultCode()) {
                    case SUCCESS_CODE://成功回调
                        if (httpResult.getData().size() == 0) {
                            resultCode = EmptyCallback.class;
                        } else {
                            resultCode = SuccessCallback.class;
                        }
                        break;
                    case ERROR_CODE:
                        resultCode = ErrorCallback.class;
                        break;
                }
                return resultCode;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // do net here...

                //callback
                loadService.showWithConvertor(mHttpResult);
            }
        }, 500);
    }

    private class HttpResult {
        private int resultCode;
        private List<Object> data;

        HttpResult(int resultCode, List<Object> data) {
            this.resultCode = resultCode;
            this.data = data;
        }

        int getResultCode() {
            return resultCode;
        }

        public List<Object> getData() {
            return data;
        }
    }
}
