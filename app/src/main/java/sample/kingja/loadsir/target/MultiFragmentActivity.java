package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sample.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class MultiFragmentActivity extends AppCompatActivity {
    private static final String TAG = "FragmentSingleActivity";
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_mutil);
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragmentA).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragmentB).commit();
        getSupportFragmentManager().beginTransaction().show(fragmentA).hide(fragmentB).commit();
    }


    public void showFragmentA(View view) {
        getSupportFragmentManager().beginTransaction().show(fragmentA).hide(fragmentB).commit();
    }

    public void showFragmentB(View view) {
        getSupportFragmentManager().beginTransaction().show(fragmentB).hide(fragmentA).commit();
    }
}
