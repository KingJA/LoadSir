package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.convertor.Convertor;

import sample.kingja.loadsir.R;
import sample.kingja.loadsir.Util;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ConvertorActivity extends AppCompatActivity {

    private LoadSir loadSir;
    private Result mResult=new Result(2);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_convertor);
        loadSir = LoadSir.call(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                Util.goLoadCallback(loadSir,SuccessCallback.class);
            }
        }, new Convertor<Result>(){
            @Override
            public Class<? extends Callback> change2Callback(Result result) {
                Class<? extends Callback> resultCode = SuccessCallback.class;
                switch (result.getResultCode()) {
                    case 0:
                        resultCode=SuccessCallback.class;
                        break;
                    case 1:
                        resultCode=EmptyCallback.class;
                        break;
                    case 2:
                        resultCode=ErrorCallback.class;
                        break;
                }
                return resultCode;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSir.showWithConvertor(mResult);
            }
        }, 500);
    }
    public class Result {
        public Result(int resultCode) {
            this.resultCode = resultCode;
        }

        private int resultCode;

        public int getResultCode() {
            return resultCode;
        }

    }
}
