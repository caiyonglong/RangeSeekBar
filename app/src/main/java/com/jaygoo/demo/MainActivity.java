package com.jaygoo.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jaygoo.demo.fragments.BaseFragment;
import com.jaygoo.demo.fragments.RangeSeekBarFragment;
import com.jaygoo.demo.fragments.SingleSeekBarFragment;
import com.jaygoo.demo.fragments.VerticalSeekBarFragment;
import com.jaygoo.demo.fragments.StepsSeekBarFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String[] types = new String[]{"SINGLE", "RANGE", "STEP", "VERTICAL"};

    List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragments.clear();
        fragments.add(new SingleSeekBarFragment());
        fragments.add(new RangeSeekBarFragment());
        fragments.add(new StepsSeekBarFragment());
        fragments.add(new VerticalSeekBarFragment());

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.layout_tab);
        // 创建并设置适配器
        viewPager.setAdapter(new PagerAdapter(this));
        // 将 TabLayout 与 ViewPager2 关联
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(types[position]);
        }).attach();

        for (String s : types) {
            tabLayout.newTab().setText(s);
        }
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

        public String getTitle(int position) {
            return types[position];
        }
    }

}
