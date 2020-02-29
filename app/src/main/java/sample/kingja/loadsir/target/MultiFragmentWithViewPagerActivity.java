package sample.kingja.loadsir.target;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import sample.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class MultiFragmentWithViewPagerActivity extends AppCompatActivity {
    private List<Fragment>fragments=new ArrayList<>();
    private String[]tabTitles={"Fragment A","Fragment B"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewpager);
        ViewPager viewpager =  findViewById(R.id.viewpager);
        TabLayout tablayout =  findViewById(R.id.tablayout);

        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        PagerAdapter(FragmentManager fm) {
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

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }





}
