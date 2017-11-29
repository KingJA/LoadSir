package sample.kingja.loadsir.target;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import sample.kingja.loadsir.callback.ErrorCallback;
import sample.kingja.loadsir.callback.LoadingCallback;

import sample.kingja.loadsir.PostUtil;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.base.BaseFragment;

/**
 * Description:TODO
 * Create Time:2017/9/5 13:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public  class FragmentA extends BaseFragment {
    @BindView(R.id.tv_result_a)
    TextView mTvResultA;
    @Override
    protected int onCreateFragmentView() {
        return R.layout.fragment_a_content;
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
        Toast.makeText(getContext(),"reload in Fragment A",Toast.LENGTH_SHORT).show();
        mBaseLoadService.showCallback(LoadingCallback.class);
        //do retry logic...

        //callback
        PostUtil.postSuccessDelayed(mBaseLoadService);
    }
}