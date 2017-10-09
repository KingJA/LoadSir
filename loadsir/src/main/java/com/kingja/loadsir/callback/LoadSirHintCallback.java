package com.kingja.loadsir.callback;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.loadsir.R;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2017/10/9 14:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSirHintCallback extends Callback implements Serializable {

    private String title;
    private String subTitle;
    private int imgResId;
    private String retryText;
    private OnRetryListener onRetryListener;

    public LoadSirHintCallback(String title, String subTitle, int imgResId, String retryText, OnRetryListener
            onRetryListener) {
        this.title = title;
        this.subTitle = subTitle;
        this.imgResId = imgResId;
        this.retryText = retryText;
        this.onRetryListener = onRetryListener;
    }

    @Override
    protected int onCreateView() {
        return R.layout.item_loadsir__empty_error_timeout;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvSubtitle = (TextView) view.findViewById(R.id.tvSubtitle);
        TextView tvAction = (TextView) view.findViewById(R.id.tvAction);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(subTitle)) {
            tvSubtitle.setVisibility(View.VISIBLE);
            tvSubtitle.setText(subTitle);
        }
        if (imgResId != -1) {
            ivImage.setVisibility(View.VISIBLE);
            ivImage.setBackgroundResource(imgResId);
        }
        if (onRetryListener != null) {
            tvAction.setVisibility(View.VISIBLE);
            tvAction.setText(retryText);
            onRetryListener.onRetry();
        }
    }

    public static class Builder implements Serializable {
        private String title;
        private String subTitle;
        private int imgResId = -1;
        private String retryText;
        private OnRetryListener onRetryListener;

        public Builder setHintImg(@DrawableRes int imgResId) {
            this.imgResId = imgResId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }


        public Builder setRetry(String retryText, OnRetryListener onRetryListener) {
            this.retryText = retryText;
            this.onRetryListener = onRetryListener;
            return this;
        }

        public LoadSirHintCallback build() {
            return new LoadSirHintCallback(title, subTitle, imgResId, retryText, onRetryListener);
        }
    }
}
