package sample.kingja.loadsir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sample.kingja.loadsir.target.MultiFragmentActivity;
import sample.kingja.loadsir.target.FragmentSingleActivity;
import sample.kingja.loadsir.target.NormalActivity;
import sample.kingja.loadsir.target.ConvertorActivity;
import sample.kingja.loadsir.target.MultiFragmentWithViewPagerActivity;
import sample.kingja.loadsir.target.PlaceholderActivity;
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
        startActivity(new Intent(this, NormalActivity.class));
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
        startActivity(new Intent(this,MultiFragmentWithViewPagerActivity.class));
    }


    public void inFragmentMutil(View view) {
        startActivity(new Intent(this,MultiFragmentActivity.class));
    }

    public void showPlaceholder(View view) {
        startActivity(new Intent(this,PlaceholderActivity.class));
    }
}
