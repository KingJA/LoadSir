package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import sample.kingja.loadsir.R;

/**
 * Description:
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class KeepTitleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragment);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new KeepTitleFragment()).commit();
    }


}
