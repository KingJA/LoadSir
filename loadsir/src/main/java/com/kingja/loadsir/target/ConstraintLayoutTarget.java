package com.kingja.loadsir.target;

import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ConstraintLayoutTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return (((View) target).getParent() instanceof ConstraintLayout);
    }

    @Override
    public LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener) {
        View oldContent = (View) target;
        ViewGroup contentParent = (ViewGroup) (oldContent.getParent());
        int childIndex = 0;
        int childCount = contentParent == null ? 0 : contentParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (contentParent.getChildAt(i) == oldContent) {
                childIndex = i;
                break;
            }
        }
        if (contentParent != null) {
            contentParent.removeView(oldContent);
        }
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(oldContent.getContext(), onReloadListener);
        if (oldLayoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout constraintLayout = new ConstraintLayout(oldContent.getContext());
            constraintLayout.addView(oldContent, oldLayoutParams);
            oldContent = constraintLayout;
        }
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, oldContent.getContext(),onReloadListener));
        contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        return loadLayout;
    }
}
