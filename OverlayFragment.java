package com.shakespiker.screensaver;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

public class OverlayFragment extends Fragment {
WebView webView;
    public static OverlayFragment newInstance() {
        OverlayFragment fragment = new OverlayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overlay, container, false);

       // ImageView close = (ImageView) view.findViewById(R.id.image);

//        close.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });
////
        webView=(WebView)view.findViewById(R.id.clockView);
//        webView.loadUrl("http://liveappsbusiness.in/");
        webView.loadUrl("file:///android_asset/index.html");


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.clockView && event.getAction() == MotionEvent.ACTION_MOVE){
                    getActivity().finish();
                }
                return false;
            }
        });




        return view;
    }
}