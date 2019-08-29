package com.kingja.loadsir.targetconvertor;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.core.TargetContext;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ActivityTargetConvertor implements ITargetConvertor {
    @Override
    public TargetContext getTargetContext(Object target) {
        Activity activity = (Activity) target;
        ViewGroup contentParent = activity.findViewById(android.R.id.content);
        int childIndex = 0;
        View oldContent = contentParent.getChildAt(childIndex);
        contentParent.removeView(oldContent);
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        return new TargetContext(activity, contentParent, oldContent, childIndex, oldLayoutParams);
    }

    @Override
    public boolean stanceof(Object target) {
        return target instanceof Activity;
    }
}
