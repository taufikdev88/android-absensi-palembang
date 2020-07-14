package com.example.absensipolsri;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

public class StreamActivity extends AppCompatActivity {
    String url = "http://13.67.75.133/absensi";

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WebView webView = new WebView(getApplicationContext());
        setContentView(webView);
        webView.loadUrl(url);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCachePath(StreamActivity.this.getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("Original2",getApplicationContext().getCacheDir().getAbsolutePath());
                customTabsIntent.launchUrl(getApplicationContext(), Uri.parse(webView.getUrl()));
            }
        });
    }
}