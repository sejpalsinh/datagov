package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.EditText;

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

public class BloodBank extends AppCompatActivity {
    ArrayList<Doctor> mExampleList;
    RecyclerView recyclerView;
    SqliteDatabse sqliteDatabse;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String url = "http://rmcfindhospital.dx.am/blooddata.php";
    BloodAdapter bloodAdapter;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        recyclerView =findViewById(R.id.blood_list);
        editText = findViewById(R.id.edserch);
        mExampleList = new ArrayList<Doctor>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        sqliteDatabse = new SqliteDatabse(getApplicationContext());
        String result = sqliteDatabse.showBloodbank();


        try {
            fillBloodbankFromDB(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void fillBloodbankFromDB(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        mExampleList.clear();
        Log.i("arrLen", String.valueOf(jsonArray.length()));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            int id = json.getInt("b_id");
            String name = json.getString("b_name");
            Doctor iteam = new Doctor(id,name);
            mExampleList.add(iteam);
        }
        bloodAdapter = new BloodAdapter(mExampleList,this);
        recyclerView.setAdapter(bloodAdapter);
    }
    private void filter(String text) {
        ArrayList<Doctor> filteredList = new ArrayList<Doctor>();

        for (Doctor i : mExampleList) {
            if (i.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(i);
            }
        }
        bloodAdapter.filterList(filteredList);
    }
//    public void fetchDataFromInternet() {
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        requestQueue = Volley.newRequestQueue(this);
//        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("bloodlist");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject json = jsonArray.getJSONObject(i);
//                        int id = json.getInt("b_id");
//                        String name = json.getString("b_name");
//                        Doctor iteam = new Doctor(id,name);
//                        mExampleList.add(iteam);
//                    }
//                    bloodAdapter = new BloodAdapter(mExampleList,getApplicationContext());
//                    recyclerView.setAdapter(bloodAdapter);
//                    progressDialog.dismiss();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//        requestQueue.add(stringRequest);
//    }

}
