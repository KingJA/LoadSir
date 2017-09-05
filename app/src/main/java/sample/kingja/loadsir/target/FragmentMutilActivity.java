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

public class FragmentMutilActivity extends AppCompatActivity {
    private static final String TAG = "FragmentSingleActivity";
    private LoadSirFragmentA loadSirFragmentA;
    private LoadSirFragmentB loadSirFragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_mutil);
        loadSirFragmentA = new LoadSirFragmentA();
        loadSirFragmentB = new LoadSirFragmentB();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, loadSirFragmentA).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, loadSirFragmentB).commit();
        getSupportFragmentManager().beginTransaction().show(loadSirFragmentA).hide(loadSirFragmentB).commit();
    }


    public void goFragmentA(View view) {
        getSupportFragmentManager().beginTransaction().show(loadSirFragmentA).hide(loadSirFragmentB).commit();
    }

    public void goFragmentB(View view) {
        getSupportFragmentManager().beginTransaction().show(loadSirFragmentB).hide(loadSirFragmentA).commit();
    }
}
