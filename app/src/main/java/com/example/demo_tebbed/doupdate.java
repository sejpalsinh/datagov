package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class doupdate extends AppCompatActivity {
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String url = " ";
    String d_name =" ";
    String d_email= " ";
    String d_details= " ";
    String d_speciality= " ";
    String d_time= " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doupdate);


    }
}
