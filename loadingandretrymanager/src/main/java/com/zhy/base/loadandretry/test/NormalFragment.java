package com.zhy.base.loadandretry.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;
import com.zhy.base.loadandretry.R;

/**
 * Created by zhy on 15/8/27.
 */
public class NormalFragment extends android.support.v4.app.Fragment
{
    LoadingAndRetryManager mLoadingAndRetryManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener()
        {
            @Override
            public void setRetryEvent(View retryView)
            {
                NormalFragment.this.setRetryEvent(retryView);
            }
        });

        mLoadingAndRetryManager.showLoading();
    }


    public void setRetryEvent(View retryView)
    {
        View view = retryView.findViewById(R.id.id_btn_retry);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "retry event invoked", Toast.LENGTH_SHORT).show();
                mLoadingAndRetryManager.showLoading();
                loadData();
            }
        });
    }

    private void loadData()
    {


        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (Math.random() > 0.6)
                {
                    mLoadingAndRetryManager.showContent();
                } else
                {
                    mLoadingAndRetryManager.showRetry();
                }
            }
        }.start();
    }
}


