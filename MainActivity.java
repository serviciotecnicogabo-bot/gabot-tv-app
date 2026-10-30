package com.gabot.streaming;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebChromeClient.CustomViewCallback fsCallback;
    private View fsView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false); // autoplay de video
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);

        // Mantiene la navegacion dentro de la app (no abre el navegador externo)
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
                return false;
            }
        });

        // Soporte de video a pantalla completa
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onShowCustomView(View view, CustomViewCallback cb) {
                fsView = view;
                fsCallback = cb;
                ((FrameLayout) getWindow().getDecorView())
                        .addView(view, new ViewGroup.LayoutParams(-1, -1));
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onHideCustomView() {
                if (fsView != null) {
                    ((FrameLayout) getWindow().getDecorView()).removeView(fsView);
                    fsView = null;
                }
                if (fsCallback != null) { fsCallback.onCustomViewHidden(); fsCallback = null; }
                webView.setVisibility(View.VISIBLE);
            }
        });

        if (savedInstanceState == null) {
            webView.loadUrl(getString(R.string.server_url));
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    // El boton "Atras" del control navega atras en la web
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        if (webView != null) webView.saveState(out);
    }
}
