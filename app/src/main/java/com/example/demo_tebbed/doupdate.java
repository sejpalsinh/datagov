package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class doupdate extends AppCompatActivity {
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String hurl  = "http://findhospital.tonysolutions.co/android/hospitaldata.php";
    String furl  = "http://findhospital.tonysolutions.co/android/facility.php";
    String burl  = "http://findhospital.tonysolutions.co/android/bloodbank.php";
    String durl  = "http://findhospital.tonysolutions.co/android/doctor.php";

    int h_id;
    String h_name = " ";
    String h_pgflag = " ";
    String h_address = " ";
    String h_state = " ";
    String h_dist = " ";
    String h_number = " ";
    String h_email = " ";
    String h_website = " ";
    String h_location = " ";
    String h_time = " ";

    int f_id;
    String f_name = " ";
    int fh_id;

    int d_id;
    String d_name = " ";
    String d_email = " ";
    String d_details = " ";
    String d_speciality = " ";
    int dh_id;

    int b_id;
    String b_name = " ";
    String b_email = " ";
    String b_address = " ";
    String b_website = " ";
    String b_location = " ";
    String b_number = " ";
    String b_state = " ";
    String b_dist = " ";
    int b_ap;
    int b_an;
    int b_bp;
    int b_bn;
    int b_op;
    int b_on;
    int b_abp;
    int b_abn;

    static public SqliteDatabse sqbd;
    boolean hadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doupdate);
        upDateNow();
        startActivity(new Intent(getApplicationContext(),First_Screen.class));
        finish();


//        Cursor c = sqbd.showData();
//        if(c.getCount() == 0){
//            return;
//        }else{
//            StringBuffer buffer = new StringBuffer();
//            while(c.moveToNext()){
//                buffer.append("ID : "+c.getString(5)+" \nName : "+c.getString(1)+"\nPassword : "+c.getString(2)+"\n");
//                Log.i("skjhf",buffer.toString());
//                Toast.makeText(this, ""+buffer.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//        }

    }

    public void fetchDataBloodbank() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(doupdate.this);
        stringRequest = new StringRequest(Request.Method.GET, burl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("bloodbank");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        b_id = Integer.parseInt(jsonObject.getString("b_id"));
                        b_name = jsonObject.getString("b_name");
                        b_email = jsonObject.getString("b_email");
                        b_address = jsonObject.getString("b_address");
                        b_website = jsonObject.getString("b_website");
                        b_location = jsonObject.getString("b_location");
                        b_number = jsonObject.getString("b_number");
                        b_state = jsonObject.getString("b_state");
                        b_dist = jsonObject.getString("b_dist");
                        b_ap = Integer.parseInt(jsonObject.getString("b_ap"));
                        b_an = Integer.parseInt(jsonObject.getString("b_an"));
                        b_bp = Integer.parseInt(jsonObject.getString("b_bp"));
                        b_bn = Integer.parseInt(jsonObject.getString("b_bn"));
                        b_op = Integer.parseInt(jsonObject.getString("b_op"));
                        b_on = Integer.parseInt(jsonObject.getString("b_on"));
                        b_abp =Integer.parseInt(jsonObject.getString("b_abp"));
                        b_abn =Integer.parseInt(jsonObject.getString("b_abn"));

                        System.out.println("bloodbank"+response);
                        hadd = sqbd.addBloodbank(b_id,b_name,b_email,b_address,b_website,b_location,b_number,b_state,b_dist,b_ap,b_an,b_bp,b_bn,b_op,b_on,b_abp,b_abn);
                    }
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

    public void fetchDataHospital() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(doupdate.this);
        stringRequest = new StringRequest(Request.Method.GET, hurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        h_id = Integer.parseInt(jsonObject.getString("h_id"));
                        h_name = jsonObject.getString("h_name");
                        h_pgflag = jsonObject.getString("h_pgflag");
                        h_address = jsonObject.getString("h_address");
                        h_state = jsonObject.getString("h_state");
                        h_dist = jsonObject.getString("h_dist");
                        h_number = jsonObject.getString("h_number");
                        h_email = jsonObject.getString("h_email");
                        h_website = jsonObject.getString("h_website");
                        h_location = jsonObject.getString("h_location");
                        h_time = jsonObject.getString("h_time");

                        hadd = sqbd.addHospital(h_id,h_name,h_pgflag,h_address,h_state,h_dist,h_number,h_email,h_website,h_location,h_time);
                    }

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

    public void fetchDataFacilities() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(doupdate.this);
        stringRequest = new StringRequest(Request.Method.GET, furl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("facility");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        f_id = jsonObject.getInt("f_id");
                        f_name = jsonObject.getString("f_name");
                        fh_id = jsonObject.getInt("h_id");
                        hadd = sqbd.addFacilities(f_id,f_name,fh_id);
                    }
                    System.out.println("facilities");
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
    public void fetchDataDoctor() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(doupdate.this);
        stringRequest = new StringRequest(Request.Method.GET, durl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("doctor");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        d_id = jsonObject.getInt("d_id");
                        d_name = jsonObject.getString("d_name");
                        d_email = jsonObject.getString("d_email");
                        d_details = jsonObject.getString("d_details");
                        d_speciality = jsonObject.getString("d_speciality");
                        dh_id = jsonObject.getInt("h_id");
                        hadd = sqbd.addDoctor(d_id,d_name,d_email,d_details,d_speciality,dh_id);
                    }
                    System.out.println("doctor");
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

    public void updateData(View view) {

    }
    public void upDateNow()
    {
        sqbd = new SqliteDatabse(this);
        sqbd.deleteAll();
        sqbd = new SqliteDatabse(this);
        fetchDataHospital();
        fetchDataBloodbank();
        fetchDataFacilities();
        fetchDataDoctor();
        startActivity(new Intent(getApplicationContext(),First_Screen.class));
        finish();
    }

    public void temp_bb(View view) {
        startActivity(new Intent(getApplicationContext(),Bloodbank_show.class));
    }
}

