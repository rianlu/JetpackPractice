package com.wzl.bottomnavigationvpdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RadioGroup + ViewPager
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private RadioGroup radioGroup;
    private List<Fragment> mFragments;
    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        viewPager = findViewById(R.id.viewPager);
        radioGroup = findViewById(R.id.radioGroup);

        mFragments = new ArrayList<>(3);
        mFragments.add(FirstFragment.newInstance("导航1"));
        mFragments.add(SecondFragment.newInstance("导航2"));
        mFragments.add(ThirdFragment.newInstance("导航3"));

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(mAdapter);

        viewPager.addOnPageChangeListener(mPageChangeListener);
        radioGroup.setOnCheckedChangeListener(mCheckedChangeListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            mList = list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mList == null ? null : mList.get(position);
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private RadioGroup.OnCheckedChangeListener mCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                // group.getChildAt(i).getId()
                // group.getCheckedRadioButtonId()
                // 这两个获得的id是一样的
                if (group.getChildAt(i).getId() == checkedId) {
                    viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

}
