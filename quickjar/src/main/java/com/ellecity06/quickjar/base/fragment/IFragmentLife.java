package com.ellecity06.quickjar.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Fragment 生命周期控制
 *
 * @author: ellecity06
 * @e-mail: ellecity06@sina.com
 * @time: 2018/9/25 17:02
 */

public interface IFragmentLife {

    void onAttach(Fragment fragment, Context context);

    void onCreate(Bundle savedInstanceState);

    void onCreateView(View view, Bundle savedInstanceState);

    void onActivityCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(Bundle outState);

    void onDestroyView();

    void onDestroy();

    void onDetach();

    boolean isAdded();

}
