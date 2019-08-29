package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:TODO
 * Create Time:2017/9/4 16:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TargetContext {
    private Context context;
    private ViewGroup parentView;
    private View oldContent;
    private int childIndex;
    private ViewGroup.LayoutParams layoutParams;

    public TargetContext(Context context, ViewGroup parentView, View oldContent, int childIndex,ViewGroup.LayoutParams layoutParams) {
        this.context = context;
        this.parentView = parentView;
        this.oldContent = oldContent;
        this.childIndex = childIndex;
        this.layoutParams = layoutParams;
    }

    public Context getContext() {
        return context;
    }

    View getOldContent() {
        return oldContent;
    }

    int getChildIndex() {
        return childIndex;
    }

    ViewGroup getParentView() {
        return parentView;
    }

    public ViewGroup.LayoutParams getLayoutParams() {
        return layoutParams;
    }
}
