package sample.kingja.loadsir.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;

import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

/**
 * Description:TODO
 * Create Time:2017/9/6 14:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseFragment extends Fragment {

    protected LoadService mBaseLoadService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = View.inflate(getActivity(), onCreateFragmentView(), null);
        mBaseLoadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onNetReload(v);
            }
        });
        return mBaseLoadService.getLoadLayout();
    }

    protected abstract int onCreateFragmentView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadNet();
    }

    protected abstract void loadNet();

    protected abstract void onNetReload(View v);
}
