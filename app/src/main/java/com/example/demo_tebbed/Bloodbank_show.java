package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

public class Bloodbank_show extends AppCompatActivity {

    WebView vistaWeb ;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodbank_show);
        vistaWeb  = findViewById(R.id.wb);
        vistaWeb.clearCache(true);
        vistaWeb.clearHistory();
        vistaWeb.getSettings().setJavaScriptEnabled(true);
        vistaWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        int id = 2;
        vistaWeb .loadUrl("http://findhospital.tonysolutions.co/showbloodbank.php?b_id="+id);
    }
}
