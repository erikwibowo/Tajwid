package com.luwakode.tajwid.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.luwakode.tajwid.R;
import com.luwakode.tajwid.util.PrefManager;

public class WebView extends BaseActivity {

    Bundle bundle;
    android.webkit.WebView webView;
    ProgressBar progressBar;
    PrefManager prefManager;
    String id_user;
    RelativeLayout layout_akun;
    Button btn_masuk, btn_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findView();
        initView();
        initListener();
        chat();
    }

    @Override
    public void onResume(){
        super.onResume();
        chat();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void findView() {
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress);
        layout_akun = findViewById(R.id.layout_akun);
        btn_masuk = findViewById(R.id.btn_masuk);
        btn_daftar = findViewById(R.id.btn_daftar);
    }

    @Override
    public void initView() {
        bundle = getIntent().getExtras();
        getSupportActionBar().setTitle(bundle.getString("title"));


    }

    private void chat(){
        if (bundle.getString("title").equals("Chat")){
            prefManager = new PrefManager(getApplicationContext());
            id_user = prefManager.getIdUser();
            if (prefManager.getLoginStatus()) {
                layout_akun.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                load_web(bundle.getString("url")+id_user);
            }else{
                webView.setVisibility(View.GONE);
                layout_akun.setVisibility(View.VISIBLE);
            }
        }else{
            layout_akun.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            load_web(bundle.getString("url"));
        }

    }

    @Override
    public void initListener() {
        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Daftar.class);
                startActivity(i);
            }
        });
    }

    private void settings(){
        WebSettings web_settings = webView.getSettings();
        web_settings.setJavaScriptEnabled(true);
        web_settings.setAllowContentAccess(true);
        web_settings.setUseWideViewPort(true);
        web_settings.setLoadsImagesAutomatically(true);
        web_settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        web_settings.setEnableSmoothTransition(true);
        web_settings.setDomStorageEnabled(true);
    }

    private void load_website(){
        if(Build.VERSION.SDK_INT >= 19){
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }else{
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        //Tambahkan WebChromeClient
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(android.webkit.WebView view, int newProgress) {
                //ProgressBar akan Terlihat saat Halaman Dimuat
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    //ProgressBar akan Menghilang setelah Valuenya mencapat 100%
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                //ProgressBar akan Menghilang setelah Halaman Selesai Dimuat
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    private void load_web(final String url){
        settings();
        load_website();
        webView.loadUrl(url);
    }
}