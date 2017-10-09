package com.kingja.loadsir.callback;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/10/9 14:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSirLoadingCallback extends Callback {

    private String title;
    private String subTitle;

    public LoadSirLoadingCallback(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
    }

    @Override
    protected int onCreateView() {
        return R.layout.item_loadsir__loading;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvSubtitle = (TextView) view.findViewById(R.id.tvSubtitle);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(subTitle)) {
            tvSubtitle.setVisibility(View.VISIBLE);
            tvSubtitle.setText(subTitle);
        }
    }

    public static class Builder {

        private String title;
        private String subTitle;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public LoadSirLoadingCallback build() {
            return new LoadSirLoadingCallback(this);
        }
    }
}
