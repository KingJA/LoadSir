package com.kingja.loadsir.targetconvertor;

import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.core.TargetContext;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ViewTargetConvertor implements ITargetConvertor {
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
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        return new TargetContext(oldContent.getContext(), contentParent, oldContent, childIndex, oldLayoutParams);
    }

    @Override
    public boolean stanceof(Object target) {
        return target instanceof View;
    }
}
