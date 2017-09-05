package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.EmptyCallback;
import com.kingja.loadsir.callback.ErrorCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.CustomCallback;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.Util;

/**
 * Description:TODO
 * Create Time:2017/9/5 13:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public  class LoadSirFragmentB extends Fragment {

    private static final String TAG = "LoadSirFragmentB";
    private LoadSir loadSir;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        ViewParent parent = rootView.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "view: "+view.hashCode());
        loadSir.showWithStatus(EmptyCallback.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = View.inflate(getActivity(), R.layout.fragment_b_content, null);
        loadSir = LoadSir.call(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                Util.goLoadCallback(loadSir, SuccessCallback.class);
            }
        });
        rootView= loadSir.getLoadLayout();

    }

}