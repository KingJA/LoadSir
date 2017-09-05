package sample.kingja.loadsir.target;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;

import java.util.ArrayList;
import java.util.List;

import sample.kingja.loadsir.CustomCallback;
import sample.kingja.loadsir.R;
import sample.kingja.loadsir.Util;

/**
 * Description:TODO
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class FragmentViewPagerSirActivity extends AppCompatActivity {
    private static final String TAG ="FragmentSingleActivity" ;
    private List<Fragment>fragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewpager);
        ViewPager vp_content = (ViewPager) findViewById(R.id.vp_content);
        fragments.add(new LoadSirFragmentA());
        fragments.add(new LoadSirFragmentB());
        vp_content.setAdapter(new PagerAdapter(getSupportFragmentManager()));

    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }





}
