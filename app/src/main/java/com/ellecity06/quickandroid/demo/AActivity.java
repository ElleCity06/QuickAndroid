package com.ellecity06.quickandroid.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ellecity06.quickandroid.ApiApplication;
import com.ellecity06.quickandroid.R;
import com.ellecity06.quickandroid.demo.quailfier.PoetryQualifier;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * @author ellecity06
 * @time 2018/9/19 15:07
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo
 * @des 用于演示包含方式注解@SubComponent
 */
public class AActivity extends AppCompatActivity {

    private TextView mTv_text;
    @Inject
    Gson mGson;
    @PoetryQualifier("A")
    @Inject
    Poetry mPoetry;
    @PoetryQualifier("B")
    @Inject
    Poetry mPoetryB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // 直接使用ApiApplication。因为AComponent已经包含在里面了
        ApiApplication.getApiApplication().getACompoment().inject(this);
        String text = mPoetry.getPemo() + ",mPoetryA:" + mPoetry +
                mPoetryB.getPemo() + ",mPoetryB:" + mPoetryB +
                (mGson == null ? "Gson没被注入" : "Gson已经被注入");


        mTv_text = findViewById(R.id.tv_text);
        mTv_text.setText(text);
    }
}
