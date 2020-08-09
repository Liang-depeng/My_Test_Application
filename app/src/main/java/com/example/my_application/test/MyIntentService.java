package com.example.my_application.test;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    private static ReceiveListener listenerx;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super("thread1");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (listenerx != null) {
            listenerx.setStr(intent != null ? intent.getStringExtra("name") : "null");
        }
    }

    public interface ReceiveListener {
        void setStr(String s);
    }

    public static void setListener(ReceiveListener listener) {
        listenerx = listener;
    }
}
