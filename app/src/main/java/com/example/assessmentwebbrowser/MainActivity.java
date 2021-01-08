package com.example.assessmentwebbrowser;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

EditText url_field;
Button refrbtn, gobtn ;
WebView webView;
    ProgressBar progressBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListner
            =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_left:
                    onBackPressed();
                    return true;
                case R.id.navigation_right:
                    onForwardPressed();
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url_field =findViewById(R.id.url_field);
        refrbtn=(Button)findViewById(R.id.refrbtn);
        gobtn=(Button)findViewById(R.id.gobtn);
        webView=findViewById(R.id.webView);
        progressBar= findViewById(R.id.progressBar);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.co.nz");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        refrbtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String url=url_field.getText().toString();
                if(!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                webView.loadUrl(url);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
            }
        });

        gobtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                String url=url_field.getText().toString();

                if(!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                    webView.loadUrl(url);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // TODO Auto-generated method stub
                //super.onReceivedError(view, errorCode, description, failingUrl);
                webView.loadUrl("https://www.google.com/search?q="+failingUrl);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                Toast.makeText(MainActivity.this, "Invalid URL" , Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListner);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
    }

    public void onForwardPressed(){
        if (webView.canGoForward()){
            webView.goForward();
        }
        else {
            Toast.makeText(this, "Cant go forward" , Toast.LENGTH_SHORT).show();
        }
    }
}