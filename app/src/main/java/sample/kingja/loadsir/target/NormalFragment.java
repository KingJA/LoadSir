package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;

import sample.kingja.loadsir.callback.CustomCallback;
import sample.kingja.loadsir.callback.LoadingCallback;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/9/5 13:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NormalFragment extends Fragment {

    private LoadService loadService;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_a_content, null);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new CustomCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                //do retry logic...

                //callback
                PostUtil.postSuccessDelayed(loadService);
            }
        });
        return loadService.getLoadLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PostUtil.postCallbackDelayed(loadService, CustomCallback.class);
    }

}