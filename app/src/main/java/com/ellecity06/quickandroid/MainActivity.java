package com.ellecity06.quickandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ellecity06.quickandroid.demo.OtherActivity;
import com.ellecity06.quickandroid.demo.Poetry;
import com.ellecity06.quickandroid.demo.component.MainComponent;
import com.ellecity06.quickjar.QuickAndroid;
import com.ellecity06.quickjar.http.callback.SimpleCallBack;
import com.ellecity06.quickjar.http.exception.ApiException;
import com.ellecity06.quickjar.http.utils.HttpLog;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    // @Inject表示这个对象是需要注入的，我们还需要一个连接器Component来生成实例
    @Inject
    Poetry mPoetry;
    // 这个GSON是在MainModule里注入的
    @Inject
    Gson mGson;
    private TextView mTv_text;
    private Button mBtn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_text = findViewById(R.id.tv_text);
        mBtn_jump = findViewById(R.id.btn_jump);
        mBtn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuickAndroid.ellecityHttp().post("/api?service_name=mbm_contract_fdd_req")
                        .baseUrl("http://10.120.52.43:8080")
                        .upJson("{\n" +
                                "     \"godId\":10000010,\n" +
                                "\t \"contractId\": 100971,\n" +
                                "     \"tokenId\": \"MTAwMDAwMTk1NTIzNkYxRDY5NUY0NTM4RkYzRDNENzE5OEFBMjlCRA==\",\n" +
                                "     \"contractType\": \"MIDDLE\",\n" +
                                "     \"creditId\":100971\n" +
                                "\n" +
                                "}")
                        //这里不想解析，简单只是为了做演示 直接返回String
                        .execute(new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException e) {

                                HttpLog.d(e.toString() + " 我错了");
                            }

                            @Override
                            public void onSuccess(String s) {
                                Log.d("sdasdasdasdasd", "返回数据===========" + s);
                                HttpLog.d("返回数据===========" + s);
                            }
                        });
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
            }
        });
        //事实证明这个方式的单例是不成立的。所以使用到@Scope注解在定义作用域
        MainComponent.getInstance().inject(this);
        //        DaggerMainComponent.builder()
        //                .build()
        //                .inject(this);
        String json = mGson.toJson(mPoetry);
        mTv_text.setText(mGson.toJson(mPoetry) + ",mPoetry:" + mPoetry);

    }
}
