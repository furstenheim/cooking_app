package io.github.furstenheim.cookingapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;

import io.github.furstenheim.store.ThreadExecutor;
import io.github.furstenheim.store.ThreadExecutorService;

public class MainThread implements ThreadExecutor {
    WeakReference<Context> context;

    final Handler mHandler = new Handler();

    public MainThread(WeakReference<Context> context) {
        this.context = context;
    }

    @Override
    public void execute(Runnable runnable) {
        Context context = this.context.get();
        if (context != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                runnable.run();
            } else {
                mHandler.post(runnable);
            }
        }
    }
}
