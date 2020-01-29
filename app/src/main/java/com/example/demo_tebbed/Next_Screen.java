package com.example.demo_tebbed;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Next_Screen extends AppCompatActivity {

    TextView add, num, web, loc, time, email;
    TextView hname, show_facility, fac;
    String dname = "";
    String str;
    RecyclerView recyclerView;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    DoctorAdapter doctorAdapter;
    ArrayList<Doctor> mExampleList;
    String url = "";
    String url1 = "";
    String url2 = "";
    String h_name;
    String h_add, h_number, h_email, h_website, h_locaion, h_time;
    String h_spc = "";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next__screen);
        context = getApplicationContext();
        hname = findViewById(R.id.hname);
        show_facility = findViewById(R.id.show_facility);
        num = findViewById(R.id.number);
        web = findViewById(R.id.website);
        loc = findViewById(R.id.locationlink);
        time = findViewById(R.id.time);
        email = findViewById(R.id.email);
        add = findViewById(R.id.add);
        fac = findViewById(R.id.fac);


        recyclerView = findViewById(R.id.doctor_list);
        mExampleList = new ArrayList<Doctor>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        str = intent.getStringExtra("id");
        int h_id = Integer.parseInt(str);
        url = "http://rmcfindhospital.dx.am/getdata.php?id=" + h_id;
        url1 = "http://rmcfindhospital.dx.am/facility.php?id=" + h_id;
        url2 = "http://rmcfindhospital.dx.am/doctor.php?id=" + h_id;

        fetchDataFromInternet();
        fetchDataFromInternet1();
        fetchDataFromInternet2();

    }

    public void fetchDataFromInternet2() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(Next_Screen.this);
        stringRequest = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        int id = json.getInt("d_id");
                        String name = json.getString("d_name");
                        Doctor iteam = new Doctor(id,name);
                        mExampleList.add(iteam);
                    }
                    doctorAdapter = new DoctorAdapter(mExampleList,getApplicationContext());
                    recyclerView.setAdapter(doctorAdapter);
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }
    public void fetchDataFromInternet() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(Next_Screen.this);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        h_name = jsonObject.getString("h_name");
                        h_add= jsonObject.getString("h_address");
                        h_number = jsonObject.getString("h_number");
                        h_email = jsonObject.getString("h_email");
                        h_website = jsonObject.getString("h_website");
                        h_locaion = jsonObject.getString("h_location");
                        h_time = jsonObject.getString("h_time");
                    }
                    System.out.print("AAAA"+h_name+h_add);
                    //  Toast.makeText(Next_Screen.this, "aa "+h_name, Toast.LENGTH_SHORT).show();
                    hname.setText(h_name);
                    String values="Address:- "+h_add+"\n"+"Number:- "+h_number+"\n"+"Email:- "+h_email+"\n"+"Website:- "+h_website+"\n"+"Location:- "+h_locaion+"\n"+"Time:- "+h_time;
                    add.setText(h_add);
                    num.setText(h_number);
                    web.setText(h_website);
                    loc.setText(h_locaion);
                    time.setText(h_time);
                    email.setText(h_email);
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }

    public void fetchDataFromInternet1() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(Next_Screen.this);
        stringRequest = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        h_spc =h_spc+"  "+jsonObject.getString("f_name");
                    }
                    fac.setText(h_spc);
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }





}
