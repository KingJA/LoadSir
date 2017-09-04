package sample.kingja.loadsir.target;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;

import sample.kingja.loadsir.CustomCallback;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.Util;

/**
 * Description:TODO
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class FragmentTargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, new LoadSirFragment()).commit();
    }


    public static class LoadSirFragment extends Fragment {

        private LoadSir loadSir;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
                savedInstanceState) {
            return View.inflate(getActivity(), R.layout.fragment_content, null);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            loadSir = LoadSir.call(this, new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    Util.goLoadCallback(loadSir, SuccessCallback.class);
                }
            });
            Util.goLoadCallback(loadSir, CustomCallback.class);
        }

    }
}
