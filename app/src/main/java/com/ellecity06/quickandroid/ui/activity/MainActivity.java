package com.ellecity06.quickandroid.ui.activity;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ellecity06.quickandroid.R;
import com.ellecity06.quickandroid.adapter.MyFragmentPagerAdapter;
import com.ellecity06.quickandroid.app.Constants;
import com.ellecity06.quickandroid.base.BaseActivity;
import com.ellecity06.quickandroid.databinding.NavHeaderMainBinding;
import com.ellecity06.quickandroid.ui.fragment.MainFragmentOne;
import com.ellecity06.quickandroid.ui.fragment.MainFragmentThree;
import com.ellecity06.quickandroid.ui.fragment.MainFragmentTwo;
import com.ellecity06.quickandroid.utils.PerfectClickListener;
import com.ellecity06.quickandroid.utils.SPUtils;
import com.ellecity06.quickandroid.widget.statusbar.StatusBarUtil;
import com.ellecity06.quickjar.QuickAndroid;
import com.ellecity06.quickjar.image.support.LoadOption;
import com.ellecity06.quickjar.util.ElleCityToast;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    // ================= bindView start ===============
    @BindView(R.id.view_status)
    View mViewStatus;
    @BindView(R.id.iv_title_menu)
    ImageView mIvTitleMenu;
    @BindView(R.id.ll_title_menu)
    FrameLayout mLlTitleMenu;
    @BindView(R.id.iv_title_one)
    ImageView mIvTitleOne;
    @BindView(R.id.iv_title_two)
    ImageView mIvTitleTwo;
    @BindView(R.id.iv_title_three)
    ImageView mIvTitleThree;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    // ================= bindView end ===============
    private NavHeaderMainBinding mBind;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mFab.setVisibility(View.GONE);
        ImageView iv_test = findViewById(R.id.iv_test);
//        final SmartRefreshLayout rl_ref = findViewById(R.id.rl_ref);

//        rl_ref.addEasyEvent(new ElleCityRefreshLayout.EasyEvent() {
//            @Override
//            public void onLoadMore() {
//                rl_ref.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        rl_ref.loadMoreComplete();
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onRefreshing() {
//                rl_ref.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        rl_ref.refreshComplete();
//                    }
//                }, 2000);
//            }
//        });
//        rl_ref.setLoadMoreModel(LoadModel.COMMON_MODEL);

        AnimationDrawable drawable = (AnimationDrawable) iv_test.getDrawable();
        drawable.start();


        //                        LoadOption loadOption = new LoadOption();
        //                        loadOption.setGifPlayCount(1);
        //
        //                        QuickAndroid.imageManager().loadGifListener("http://upload.gezila.com/data/20150403/95381428024782.gif", iv_test, loadOption, new GlideManager.GifListener() {
        //                            @Override
        //                            public void gifPlayComplete() {
        //                                ElleCityToast.show("Gif播放为完了");
        //                            }
        //                        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initStatusView();
        initContentFragment();
        initDrawerLayout();
    }

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = mViewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        mViewStatus.setLayoutParams(layoutParams);
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        mNavView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = mNavView.getHeaderView(0);
        mBind = DataBindingUtil.bind(headerView);
        //        mBind.setListener(MainActivity.this);
        mBind.dayNightSwitch.setChecked(SPUtils.getNightMode()); // 夜间模式，尚未完成

        LoadOption loadOption = new LoadOption();
        loadOption.setIsCircle(true);

        QuickAndroid.imageManager().loadNet(Constants.ICON_LOGO_URL, mBind.ivAvatar, loadOption);


        mBind.llNavExit.setOnClickListener(this);
        mBind.ivAvatar.setOnClickListener(this);

        mBind.llNavHomepage.setOnClickListener(mListener);
        mBind.llNavScanDownload.setOnClickListener(mListener);
        mBind.llNavDeedback.setOnClickListener(mListener);
        mBind.llNavAbout.setOnClickListener(mListener);
        mBind.llNavLogin.setOnClickListener(mListener);
        mBind.llNavCollect.setOnClickListener(mListener);


    }

    private PerfectClickListener mListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(final View v) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            mDrawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()) {
                        case R.id.ll_nav_homepage:// 主页
                            ElleCityToast.show("首页");
                            break;
                        case R.id.ll_nav_scan_download://扫码下载
                            ElleCityToast.show("扫码下载");
                            break;
                        case R.id.ll_nav_deedback:// 问题反馈
                            ElleCityToast.show("问题反馈");
                            break;
                        case R.id.ll_nav_about:// 关于
                            ElleCityToast.show("关于QuickAndroid");
                            break;
                        case R.id.ll_nav_collect:// 玩安卓收藏
                            ElleCityToast.show("玩安卓收藏");
                            break;
                        case R.id.ll_nav_login:// 玩安卓登录
                            ElleCityToast.show("玩安卓登录");
                            break;
                        default:
                            break;
                    }
                }
            }, 260);
        }
    };

    /**
     * 初始化内容
     */
    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new MainFragmentOne());
        mFragmentList.add(new MainFragmentTwo());
        mFragmentList.add(new MainFragmentThree());
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mVpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        mVpContent.setOffscreenPageLimit(2);
        mVpContent.addOnPageChangeListener(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        setCurrentItem(0);
    }

    /**
     * 切换页面
     *
     * @param position 分类角标
     */
    private void setCurrentItem(int position) {
        boolean isOne = false;
        boolean isTwo = false;
        boolean isThree = false;
        switch (position) {
            case 0:
                isOne = true;
                break;
            case 1:
                isTwo = true;
                break;
            case 2:
                isThree = true;
                break;
            default:
                isTwo = true;
                break;
        }
        mVpContent.setCurrentItem(position);
        mIvTitleOne.setSelected(isOne);
        mIvTitleTwo.setSelected(isTwo);
        mIvTitleThree.setSelected(isThree);
    }

    @Override
    protected void initEvent() {
        mLlTitleMenu.setOnClickListener(this);
        mIvTitleOne.setOnClickListener(this);
        mIvTitleTwo.setOnClickListener(this);
        mIvTitleThree.setOnClickListener(this);
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:
                // 开启菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_one:
                // 重复点击不加载，节省cpu资源
                if (mVpContent.getCurrentItem() != 0) {
                    setCurrentItem(0);
                }
                break;
            case R.id.iv_title_two:
                if (mVpContent.getCurrentItem() != 1) {
                    setCurrentItem(1);
                }
                break;
            case R.id.iv_title_three:
                if (mVpContent.getCurrentItem() != 2) {
                    setCurrentItem(2);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setCurrentItem(0);
                break;
            case 1:
                setCurrentItem(1);
                break;
            case 2:
                setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 夜间模式待完善
     */
    public boolean getNightMode() {
        return SPUtils.getNightMode();
    }

    public void onNightModeClick(View view) {
        if (!SPUtils.getNightMode()) {
            //            SkinCompatManager.getInstance().loadSkin(Constants.NIGHT_SKIN);
        } else {
            // 恢复应用默认皮肤
            //            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
        SPUtils.setNightMode(!SPUtils.getNightMode());
        mBind.dayNightSwitch.setChecked(SPUtils.getNightMode());
    }
}
