package com.kingja.loadsir.target;

import com.kingja.loadsir.core.TargetContext;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ITarget {
    /**
     *
     * @param target
     * @return
     * v1.3.8
     */
    TargetContext getTargetContext(Object target);

    /**
     *
     * @param target
     * @return
     * v1.3.8
     */
    boolean stanceof(Object target);
}
