package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class BloodBankDetails extends AppCompatActivity {
    TextView bname,bemail,baddress,bwebsite,blocation,bnumber;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String url = " ";
    String b_name =" ";
    String b_email= " ";
    String b_address = " ";
    String b_website = " ";
    String b_location = " ";
    String b_number  =" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_details);
        bname = findViewById(R.id.bname);
        bemail = findViewById(R.id.bemail);
        baddress = findViewById(R.id.badd);
        bwebsite = findViewById(R.id.bwebsite);
        blocation = findViewById(R.id.blocationlink);
        bnumber = findViewById(R.id.bnumber);

        Intent intent = getIntent();
        String bid = intent.getStringExtra("b_id");
        int id = Integer.parseInt(bid);

        url = "http://rmcfindhospital.dx.am/blood.php?id="+id;

        fetchDataFromInternet();

    }
    public void fetchDataFromInternet() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(BloodBankDetails.this);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("bloodlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        b_name =jsonObject.getString("b_name");
                        b_email= jsonObject.getString("b_email");
                        b_address = jsonObject.getString("b_address");
                        b_website = jsonObject.getString("b_website");
                        b_location = jsonObject.getString("b_location");
                        b_number  = jsonObject.getString("b_number");
                    }
                    bname.setText(b_name);
                    bemail.setText(b_email);
                    baddress.setText(b_address);
                    bwebsite.setText(b_website);
                    blocation.setText(b_location);
                    bnumber.setText(b_number);
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
