package com.kingja.loadsir.target;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.core.TargetContext;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ConstraintLayoutTarget implements ITarget {
    private  final String TAG =getClass().getSimpleName() ;

    @Override
    public TargetContext getTargetContext(Object target) {
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
        return new TargetContext(oldContent.getContext(), contentParent, oldContent, childIndex,oldContent.getLayoutParams());
    }

    @Override
    public boolean stanceof(Object target) {
        return (((View) target).getParent() instanceof ConstraintLayout);
    }
}
