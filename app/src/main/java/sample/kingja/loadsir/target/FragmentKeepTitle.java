package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.callback.EmptyCallback;
import sample.kingja.loadsir.callback.LoadingCallback;

/**
 * Description:
 * Create Time:2017/9/26 14:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FragmentKeepTitle extends Fragment {

    private LoadService loadService;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    private Unbinder unBinder;

    @OnClick(R.id.iv_back)
    public void onBack() {
        getActivity().finish();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PostUtil.postCallbackDelayed(loadService, EmptyCallback.class, 1200);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.title_title_bar, container, false);
        unBinder = ButterKnife.bind(this, rootView);
        RelativeLayout rl_titleBar = (RelativeLayout) rootView.findViewById(R.id.rl_titleBar);
        LinearLayout ll_content = (LinearLayout) rootView.findViewById(R.id.ll_content);
        rootView.removeView(ll_content);
        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .build();
        loadService = loadSir.register(ll_content, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showSuccess();
            }

        });
        return loadService.getTitleLoadLayout(getContext(), rootView, rl_titleBar, ll_content);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unBinder.unbind();
    }
}
