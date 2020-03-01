package com.kingja.loadsir;

import android.os.Looper;
import android.util.Log;

import com.kingja.loadsir.target.ITarget;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/4 16:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoadSirUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ITarget getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            Log.e("getTargetContext", "待选: "+targetContext.getClass().getSimpleName() );
            if (targetContext.equals(target)) {
                Log.e("getTargetContext", "选中: "+targetContext.getClass().getSimpleName() );
                return targetContext;
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}
