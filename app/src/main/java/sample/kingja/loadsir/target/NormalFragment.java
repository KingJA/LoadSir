package sample.kingja.loadsir.target;

import android.os.Bundle;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.callback.CustomCallback;
import sample.kingja.loadsir.callback.ErrorCallback;
import sample.kingja.loadsir.callback.LoadingCallback;

/**
 * Description:TODO
 * Create Time:2017/9/5 13:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NormalFragment extends Fragment {

    private LoadService loadService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new CustomCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new ErrorCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        View rootView = inflater.inflate(R.layout.fragment_a_content, container, false);
        ButterKnife.bind(this, rootView);
        loadService = loadSir.register(rootView, new Callback.OnReloadListener() {
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
                        loadService.showCallback(CustomCallback.class);
                    }
                }).start();
            }
        });
        return loadService.getLoadLayout();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PostUtil.postCallbackDelayed(loadService, ErrorCallback.class);
    }
}