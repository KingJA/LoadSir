package sample.kingja.loadsir.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.ButterKnife;
import sample.kingja.loadsir.R;

/**
 * Description：TODO
 * Create Time：2017/3/20 14:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseTitleActivity extends AppCompatActivity {
    protected View rootView;
    protected LoadService mBaseLoadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = View.inflate(this, R.layout.activity_title, null);
        addContent();
        setContentView(rootView);
        initView();
        initNet();
    }


    private void addContent() {
        FrameLayout flContent = (FrameLayout) rootView.findViewById(R.id.fl_content);
        TextView tvTitleTitle = (TextView) rootView.findViewById(R.id.tv_title_title);
        LinearLayout llTitleBack = (LinearLayout) rootView.findViewById(R.id.ll_title_back);
        tvTitleTitle.setText(getContentTitle() == null ? "" : getContentTitle());
        llTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backClick();
            }
        });
        View content = View.inflate(this, getContentView(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(content, params);
            ButterKnife.bind(this, rootView);
            mBaseLoadService = LoadSir.getDefault().register(content, new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    onNetReload(v);
                }
            });
        }
    }

    private void backClick() {
        finish();
    }

    protected abstract String getContentTitle();

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initNet();

    protected abstract void onNetReload(View v);
}
