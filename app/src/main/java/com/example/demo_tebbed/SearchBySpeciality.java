package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
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

public class SearchBySpeciality extends AppCompatActivity {
    Spinner spserch;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    ArrayList<Iteam> mExampleList;
    CustomAdapter customAdapter;
    String url = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_speciality);
        spserch = findViewById(R.id.searchsp);

        recyclerView = findViewById(R.id.search_list);
        mExampleList = new ArrayList<Iteam>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


    }
    public void fetchDataFromInternet(String u) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue = Volley.newRequestQueue(SearchBySpeciality.this);
        stringRequest = new StringRequest(Request.Method.GET, u, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        int id = json.getInt("h_id");
                        String name = json.getString("h_name");
                        String pgflag = json.getString("h_pgflag");
                        Iteam iteam = new Iteam(id,name,pgflag);
                        mExampleList.add(iteam);
                    }
                    customAdapter = new CustomAdapter(mExampleList,getApplicationContext());
                    recyclerView.setAdapter(customAdapter);
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

    public void Btnsearch(View view){
        String sp = spserch.getSelectedItem().toString();
        url = "http://rmcfindhospital.dx.am/data.php?s="+"'"+sp+"'";
        System.out.println(url);
        fetchDataFromInternet(url);
    }
}
