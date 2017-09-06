package sample.kingja.loadsir.target;

import android.view.View;
import android.widget.Toast;

import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.LoadingCallback;
import com.kingja.loadsir.callback.SuccessCallback;

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
        Toast.makeText(getContext(),"reload in Fragment A",Toast.LENGTH_SHORT).show();
        mBaseLoadService.showWithStatus(LoadingCallback.class);
        //do retry logic...

        //callback
        PostUtil.postCallbackDelayed(mBaseLoadService, SuccessCallback.class);
    }
}