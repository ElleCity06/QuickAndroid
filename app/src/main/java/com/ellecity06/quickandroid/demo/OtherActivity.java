package com.ellecity06.quickandroid.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ellecity06.quickandroid.R;
import com.ellecity06.quickandroid.demo.component.MainComponent;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * @author ellecity06
 * @time 2018/9/19 11:11
 * @project QuickAndroid
 * @packge name：com.ellecity06.quickandroid.demo
 * @des 新建一个activity用于检测作用域的问题
 */
public class OtherActivity extends AppCompatActivity {

    private TextView mTv_text;
    @Inject
    Poetry mPoetry;
    @Inject
    Gson mGson;
    private Button mBtn_jump;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        // 测试MainComponent的单例是否起作用
        MainComponent.getInstance().inject(this);
        mTv_text = findViewById(R.id.tv_text);
        mTv_text.setText(mGson.toJson(mPoetry) + ",mPoetry:" + mPoetry);
        mBtn_jump = findViewById(R.id.btn_jump);
        mBtn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this, AActivity.class));
            }
        });
    }
}
