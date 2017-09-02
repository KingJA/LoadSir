package com.zhy.base.loadandretry.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.zhy.base.loadandretry.R;

public class FragmentTestActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.id_rl_fragment_container);

        if (fragment == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.id_rl_fragment_container, new NormalFragment()).commit();
        }
    }


}
