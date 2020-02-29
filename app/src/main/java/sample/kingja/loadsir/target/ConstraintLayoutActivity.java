package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.callback.EmptyCallback;
import sample.kingja.loadsir.callback.ErrorCallback;
import sample.kingja.loadsir.callback.LoadingCallback;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class ConstraintLayoutActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout);
        initLoadSir();
    }

    private void initLoadSir() {
        TextView tv_center = findViewById(R.id.tv_center);
        // Your can change the callback on sub thread directly.
        //如果ConstraintLayout的子控件tv_center被其它控件相对约束了，那么就会报错，因为会把tv_center的LP自动转为CL的LP，是
//        此时tv_center的LP已经是FL的LP，需要研究怎么保证tv_center的LP还是保留为CL的LP
        //LoadSir 加载第一个callback位置错误，第二个开始才正确
        loadService = LoadSir.getDefault().register(tv_center, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // Your can change the status out of Mai删除后ConstraintLayoutTargetn thread.
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
        });
//        PostUtil.postCallbackDelayed(loadService, LoadingCallback.class, 0 );
        PostUtil.postCallbackDelayed(loadService, EmptyCallback.class, 1000);
//        PostUtil.postCallbackDelayed(loadService, ErrorCallback.class, 1000);
//        PostUtil.postCallbackDelayed(loadService, LoadingCallback.class,9000);
        // default  -> LoadingCallback  -> EmptyCallback 错误
        // XCallback -> default  -> LoadingCallback  -> EmptyCallback 错误
        // XCallback -> LoadingCallback  -> EmptyCallback 正确
        //ErrorCallback 0 EmptyCallback 10 错误
        //ErrorCallback 0 EmptyCallback 20 正确 可能要等重新测量requestLayout
        //接上SuccessView丛GONE变为INVISIBLE需要重新绘制
    }
}
