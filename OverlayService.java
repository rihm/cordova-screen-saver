package com.shakespiker.screensaver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class OverlayService extends Service {

    private static final String TAG = OverlayService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerOverlayReceiver();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unregisterOverlayReceiver();
        super.onDestroy();
    }

    private void registerOverlayReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
       // filter.addAction(ACTION_DEBUG);
        registerReceiver(overlayReceiver, filter);
    }

    public void unregisterOverlayReceiver() {
        unregisterReceiver(overlayReceiver);

    }

    // am broadcast -a daichan4649.lockoverlay.action.DEBUG
    private static final String ACTION_DEBUG = "com.shakespiker.screensaver.action.DEBUG";

    private BroadcastReceiver overlayReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "[onReceive]" + action);
            if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                // ACTON_SCREEN_ON
                showOverlayActivity(context);
            }
//            else if (action.equals(ACTION_DEBUG)) {
//                showOverlayActivity(context);
//            }
        }
    };

    private void showOverlayActivity(Context context) {
        Intent intent = new Intent(context, OverlayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }
}