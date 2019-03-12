package com.shakespiker.screensaver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Screensaver extends CordovaPlugin {
Context context;
    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
        if (action.equals("setScreenSaver")){
            try {
                final String responseText = "Set successfully";
                Intent intent = new Intent(context, OverlayService.class);
                context.startService(intent);
                //Toast.makeText(context,"screensaver set successfully",Toast.LENGTH_LONG).show();
                cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.success(responseText); // Thread-safe.
                    }
                });
            } catch (JSONException e){
                callbackContext.error("Not set successfully");
            }
            return true;
        }

        if (action.equals("unSetScreenSaver")){
            try {
                final String responseText = "Unset successfully " + args.getString(0);
                context.stopService(new Intent(context, OverlayService.class));
                Toast.makeText(context,"screensaver unset successfully",Toast.LENGTH_LONG).show();
                context.startService(context.Intent);
                cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        callbackContext.success(responseText); // Thread-safe.
                    }
                });
            } catch (JSONException e){
                callbackContext.error("Somthing wroung");
            }
            return true;
        }
        return false;
    }
}
