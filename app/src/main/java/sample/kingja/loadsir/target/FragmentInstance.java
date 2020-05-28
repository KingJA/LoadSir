package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.base.BaseFragment;
import sample.kingja.loadsir.callback.ErrorCallback;
import sample.kingja.loadsir.callback.LoadingCallback;

/**
 * Description:TODO
 * Create Time:2017/9/5 13:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FragmentInstance extends BaseFragment {
    @BindView(R.id.tv_result_a)
    TextView mTvResultA;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String title;

    public static Fragment newInstance(String title) {
        FragmentInstance fragmentInstance = new FragmentInstance();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragmentInstance.setArguments(args);
        return fragmentInstance;
    }

    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_instance;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            title = getArguments().getString("title");
            tvTitle.setText(title);
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void loadNet() {
        // do net here...
        // call back
        PostUtil.postCallbackDelayed(mBaseLoadService, ErrorCallback.class);
    }

    @Override
    protected void onNetReload(View v) {
        mTvResultA.setText("Oh, Yes.");
        Toast.makeText(getContext(), String.format("reload in %s",title), Toast.LENGTH_SHORT).show();
        mBaseLoadService.showCallback(LoadingCallback.class);
        //do retry logic...

        //callback
        PostUtil.postSuccessDelayed(mBaseLoadService);
    }
}