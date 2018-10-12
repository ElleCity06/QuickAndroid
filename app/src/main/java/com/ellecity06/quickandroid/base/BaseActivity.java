package com.ellecity06.quickandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.ellecity06.quickandroid.R;
import com.ellecity06.quickandroid.mvp.BasePresenter;
import com.ellecity06.quickjar.base.activity.IBaseActivity;
import com.ellecity06.quickjar.util.ColorBar;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.ButterKnife;

/**
 * Acticity的基类
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/29 9:55
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseActivity {

    @BindColor(R.color.colorTheme)
    protected int mColor;
    @Inject
    @Nullable
    protected P mPresenter;

    protected abstract int getContentLayout();//返回页面布局id

    protected abstract void initView(Bundle savedInstanceState);//做视图相关的初始化工作

    protected abstract void initData(Bundle savedInstanceState);//做数据相关的初始化工作

    protected abstract void initEvent();//做监听事件相关的初始化工作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentLayout() != 0) {
            setContentView(getContentLayout());
            ButterKnife.bind(this);
        }
        initBarColor();//初始化状态栏/导航栏颜色，需在设置了布局后再调用
        initView(savedInstanceState);//由具体的activity实现，做视图相关的初始化
        initData(savedInstanceState);//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化
    }

    private void initBarColor() {
        ViewGroup parent = findViewById(android.R.id.content);
        if (parent.getChildAt(0) instanceof DrawerLayout) {
            ColorBar.newDrawerBuilder()
                    .applyNav(true)
                    .navColor(mColor)
                    .navDepth(0)
                    .statusColor(mColor)
                    .statusDepth(0)
                    .build(this)
                    .apply();
        } else {
            ColorBar.newColorBuilder()
                    .applyNav(true)
                    .navColor(mColor)
                    .navDepth(0)
                    .statusColor(mColor)
                    .statusDepth(0)
                    .build(this)
                    .apply();

        }
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public boolean isUseFragment() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }
}
