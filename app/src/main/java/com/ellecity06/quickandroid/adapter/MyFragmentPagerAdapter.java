package com.ellecity06.quickandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 见字如面
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/29 14:36
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<?> mFragment;
    private List<String> mTitleList;

    /**
     * 普通，主页使用
     */
    public MyFragmentPagerAdapter(FragmentManager fm, List<?> mFragment) {
        super(fm);
        this.mFragment = mFragment;
    }

    /**
     * 接收首页传递的标题
     */
    public MyFragmentPagerAdapter(FragmentManager fm, List<?> mFragment, List<String> mTitleList) {
        super(fm);
        this.mFragment = mFragment;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    /**
     * 首页显示title，每日推荐等..
     * 若有问题，移到对应单独页面
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null && position < mTitleList.size()) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }

    public void addFragmentList(List<?> fragment) {
        this.mFragment.clear();
        this.mFragment = null;
        this.mFragment = fragment;
        notifyDataSetChanged();
    }

}
