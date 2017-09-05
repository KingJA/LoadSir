package sample.kingja.loadsir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sample.kingja.loadsir.target.FragmentMutilActivity;
import sample.kingja.loadsir.target.FragmentSingleActivity;
import sample.kingja.loadsir.target.ActivityTargetActivity;
import sample.kingja.loadsir.target.ConvertorActivity;
import sample.kingja.loadsir.target.FragmentViewPagerActivity;
import sample.kingja.loadsir.target.FragmentViewPagerSirActivity;
import sample.kingja.loadsir.target.ViewTargetActivity;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inActivity(View view) {
        startActivity(new Intent(this, ActivityTargetActivity.class));
    }

    public void inActivityWithConvertor(View view) {
        startActivity(new Intent(this, ConvertorActivity.class));
    }

    public void inFragment(View view) {
        startActivity(new Intent(this, FragmentSingleActivity.class));
    }

    public void inView(View view) {
        startActivity(new Intent(this,ViewTargetActivity.class));
    }

    public void inFragmentViewSirPager(View view) {
        startActivity(new Intent(this,FragmentViewPagerSirActivity.class));
    }

    public void inFragmentViewPager(View view) {
        startActivity(new Intent(this,FragmentViewPagerActivity.class));
    }

    public void inFragmentMutil(View view) {
        startActivity(new Intent(this,FragmentMutilActivity.class));
    }
}
