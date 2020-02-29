package sample.kingja.loadsir.target;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sample.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/9/29 13:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BestPracticesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_practices);
    }

    public void mvpActivity(View view) {
    }

    public void onLottie(View view) {
        startActivity(new Intent(this, LottieActivity.class));
    }
}
