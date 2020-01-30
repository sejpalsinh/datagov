package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorList extends AppCompatActivity {
    TextView name,email,details,speciality,time;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String url = " ";
    String d_name =" ";
    String d_email= " ";
    String d_details= " ";
    String d_speciality= " ";
    String d_time= " ";

    SqliteDatabse sqliteDatabse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        name = findViewById(R.id.doc);
        email = findViewById(R.id.demail);
        details = findViewById(R.id.ddetails);
        speciality = findViewById(R.id.dspe);
        time = findViewById(R.id.dtime);

        Intent intent = getIntent();
        String did = intent.getStringExtra("d_id");
        int id = Integer.parseInt(did);

        url  = "http://rmcfindhospital.dx.am/doc.php?id="+id;
        //fetchDataFromInternet();
        fetchDataFromDB();

    }

    private void fetchDataFromDB() {
        sqliteDatabse = new SqliteDatabse(getApplicationContext());
        String docList = sqliteDatabse.ShowDoctorData(Next_scree_Update.h_id);
        try {
            JSONArray json = new JSONArray(docList);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                Log.i("adapter1", Integer.toString(i) + json.toString());

                d_name = jsonObject.getString("d_name");
                d_email= jsonObject.getString("d_email");
                d_details= jsonObject.getString("d_details");
                d_speciality= jsonObject.getString("d_speciality");
            }
            name.setText(d_name);
            email.setText(d_email);
            details.setText(d_details);
            speciality.setText(d_speciality);
            time.setText("10 hours");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fetchDataFromInternet() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(DoctorList.this);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        d_name = jsonObject.getString("d_name");
                        d_email= jsonObject.getString("d_email");
                        d_details= jsonObject.getString("d_details");
                        d_speciality= jsonObject.getString("d_speciality");
                        d_time= jsonObject.getString("d_time");
                    }
                    name.setText(d_name);
                    email.setText(d_email);
                    details.setText(d_details);
                    speciality.setText(d_speciality);
                    time.setText(d_time);
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
