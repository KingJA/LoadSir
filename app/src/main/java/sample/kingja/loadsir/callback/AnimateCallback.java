package sample.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.kingja.loadsir.callback.Callback;

import sample.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/9/3 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AnimateCallback extends Callback {

    private Context context;
    private View animateView;

    @Override
    protected int onCreateView() {
        return R.layout.layout_animate;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        super.onViewCreate(context, view);
    }

    @Override
    public void onAttach(Context context, View view) {
        this.context = context;
        animateView = view.findViewById(R.id.view_animate);
        Animation animation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setRepeatCount(Integer.MAX_VALUE);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        animateView.startAnimation(animation);
        Toast.makeText(context.getApplicationContext(), "start animation", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (animateView != null) {
            animateView.clearAnimation();
        }
        Toast.makeText(context.getApplicationContext(), "stop animation", Toast.LENGTH_SHORT).show();
    }
}
