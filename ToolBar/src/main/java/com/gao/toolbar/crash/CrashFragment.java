package com.gao.toolbar.crash;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gao.toolbar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/10/11.
 */
public class CrashFragment extends Fragment {

    @BindView(R.id.button_crash_main_thread)
    Button mButtonCrashMainThread;
    @BindView(R.id.button_crash_bg_thread)
    Button mButtonCrashBgThread;
    @BindView(R.id.button_crash_with_delay)
    Button mButtonCrashWithDelay;

    public static CrashFragment getInstance() {
        CrashFragment crashFragment = new CrashFragment();
        return crashFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crash, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.button_crash_main_thread, R.id.button_crash_bg_thread, R.id.button_crash_with_delay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_crash_main_thread:
                throw new RuntimeException("I'm a cool exception and I crashed the main thread!");
                // break;
            case R.id.button_crash_bg_thread:
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        throw new RuntimeException("I'm also cool, and I crashed the background thread!");
                    }
                }.execute();
                break;
            case R.id.button_crash_with_delay:
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            //meh
                        }
                        throw new RuntimeException("I am a not so cool exception, and I am delayed, so you can check if the app crashes when in background!)");
                    }
                }.execute();
                break;
        }
    }
}
