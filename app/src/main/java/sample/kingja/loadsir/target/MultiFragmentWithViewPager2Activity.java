package sample.kingja.loadsir.target;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import sample.kingja.loadsir.R;

/**
 * Description:TODO
 * Create Time:2017/9/4 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class MultiFragmentWithViewPager2Activity extends AppCompatActivity {
    private List<Fragment>fragments=new ArrayList<>();
    private String[]tabTitles={"Tab A","Tab B","Tab C","Tab D","Tab E","Tab F","Tab G","Tab H"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewpager2);
        ViewPager2 viewpager2 =  findViewById(R.id.viewpager2);
        TabLayout tablayout =  findViewById(R.id.tablayout);


        for (int i = 0; i < tabTitles.length; i++) {
            fragments.add(FragmentInstance.newInstance(tabTitles[i]));
        }

        viewpager2.setAdapter(new PagerAdapter (this));

        TabLayoutMediator tabLayoutMediator=  new TabLayoutMediator(tablayout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        });
        tabLayoutMediator.attach();
    }

    private class PagerAdapter extends FragmentStateAdapter {
        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }

}
