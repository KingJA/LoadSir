package com.kingja.loadsir;

import android.os.Looper;
import android.util.Log;

import com.kingja.loadsir.core.TargetContext;
import com.kingja.loadsir.target.ITarget;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/4 16:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSirUtil {
//    public static TargetContext getTargetContext(Object target) {
//        ViewGroup contentParent;
//        Context context;
//        if (target instanceof Activity) {
//            Activity activity = (Activity) target;
//            context = activity;
//            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
//        } else if (target instanceof ViewTarget) {
//            ViewTarget view = (ViewTarget) target;
//            contentParent = (ViewGroup) (view.getParent());
//            context = view.getContext();
//        } else {
//            throw new IllegalArgumentException("The target must be within Activity, Fragment, ViewTarget.");
//        }
//        int childIndex = 0;
//        int childCount = contentParent == null ? 0 : contentParent.getChildCount();
//        ViewTarget oldContent;
//        if (target instanceof ViewTarget) {
//            oldContent = (ViewTarget) target;
//            for (int i = 0; i < childCount; i++) {
//                if (contentParent.getChildAt(i) == oldContent) {
//                    childIndex = i;
//                    break;
//                }
//            }
//        } else {
//            oldContent = contentParent != null ? contentParent.getChildAt(0) : null;
//        }
//        if (oldContent == null) {
//            throw new IllegalArgumentException(String.format("enexpected error when register LoadSir in %s", target
//                    .getClass().getSimpleName()));
//        }
//        if (contentParent != null) {
//            contentParent.removeView(oldContent);
//        }
//        return new TargetContext(context, contentParent, oldContent, childIndex);
//    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static TargetContext getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            Log.e("getTargetContext", "待选: "+targetContext.getClass().getSimpleName() );
            if (targetContext.stanceof(target)) {
                Log.e("getTargetContext", "选中: "+targetContext.getClass().getSimpleName() );
                return targetContext.getTargetContext(target);
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}
