package com.zhy.base.loadandretry.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;
import com.zhy.base.loadandretry.R;

public class MainActivity extends AppCompatActivity
{
    LoadingAndRetryManager mLoadingAndRetryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener()
        {
            @Override
            public void setRetryEvent(View retryView)
            {
                MainActivity.this.setRetryEvent(retryView);
            }
        });

        loadData();

    }

    private void loadData()
    {
        mLoadingAndRetryManager.showLoading();

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
                double v = Math.random();
                if (v > 0.8)
                {
                    mLoadingAndRetryManager.showContent();
                } else if (v > 0.4)
                {
                    mLoadingAndRetryManager.showRetry();
                } else
                {
                    mLoadingAndRetryManager.showEmpty();
                }
            }
        }.start();


    }


    public void setRetryEvent(View retryView)
    {
        View view = retryView.findViewById(R.id.id_btn_retry);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "retry event invoked", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
    }
}
