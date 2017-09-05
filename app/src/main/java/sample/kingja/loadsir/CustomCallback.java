package sample.kingja.loadsir;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.kingja.loadsir.callback.Callback;

/**
 * Description:TODO
 * Create Time:2017/9/3 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CustomCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }

    @Override
    protected boolean onSingleRetry(Context context, View view) {
        Toast.makeText(context.getApplicationContext(),"Hello mother fuck! :p",Toast.LENGTH_SHORT).show();
        return true;
    }
}
