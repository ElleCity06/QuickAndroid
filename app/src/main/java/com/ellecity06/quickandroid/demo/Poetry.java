package com.ellecity06.quickandroid.demo;

import javax.inject.Inject;

public class Poetry {
    private String mPemo;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去.
    // @Provides 的优先级比@Inject高，应该是说@Module的优先级比@Inject高
    @Inject
    public Poetry() {
        mPemo = "生活就像海洋";
    }

    public Poetry(String s) {
        mPemo = s;
    }

    public String getPemo() {
        return mPemo;
    }
}
