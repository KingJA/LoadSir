package com.zhy.base.loadandretry.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.base.loadandretry.LoadingAndRetryManager;
import com.zhy.base.loadandretry.OnLoadingAndRetryListener;
import com.zhy.base.loadandretry.R;

public class AnyViewTestActivity extends AppCompatActivity
{

    private TextView mTextView;

    LoadingAndRetryManager mLoadingAndRetryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyview_test);

        mTextView = (TextView) findViewById(R.id.id_textview);

        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mTextView, new OnLoadingAndRetryListener()
        {
            @Override
            public void setRetryEvent(View retryView)
            {
                retryRefreashTextView(retryView);
            }
        });

        refreashTextView();


    }

    private void refreashTextView()
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

    public void retryRefreashTextView(View retryView)
    {
        View view = retryView.findViewById(R.id.id_btn_retry);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(AnyViewTestActivity.this, "retry event invoked", Toast.LENGTH_SHORT).show();
                AnyViewTestActivity.this.refreashTextView();
            }
        });
    }


}
