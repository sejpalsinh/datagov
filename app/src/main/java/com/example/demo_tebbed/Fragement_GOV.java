package com.example.demo_tebbed;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragement_GOV extends Fragment {
    ArrayList<Iteam> mExampleList;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    EditText editText;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    SqliteDatabse sqliteDatabse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragement_all,null);
        recyclerView =(RecyclerView)view.findViewById(R.id.recycle_list);
        editText = view.findViewById(R.id.edtserch);
        mExampleList = new ArrayList<Iteam>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        sharedPreferences = getContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean all = sharedPreferences.getBoolean("all", true);

        sqliteDatabse = new SqliteDatabse(getActivity());


        if(all){
            try {
                String result = sqliteDatabse.showAllGovtHospitals();
                fetchGovtHospitalsDB(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            String state = sharedPreferences.getString("state", "");
            String district = sharedPreferences.getString("district", "");

            if(state.equals("") || state.equals("Select State")){
                String result = sqliteDatabse.showAllGovtHospitals();
                System.out.println(result);
                try {
                    fetchGovtHospitalsDB(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                String result = sqliteDatabse.showAllGovtHospitals(state, district);
                System.out.println(result);
                try {
                    fetchGovtHospitalsDB(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

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

        return view;
    }

    private void fetchAllGovtHospitalsDB(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        mExampleList.clear();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Log.i("adapter1", Integer.toString(i) + json.toString());
            int id = json.getInt("h_id");
            String name = json.getString("h_name");
            String pgflag = json.getString("h_pgflag");
            Iteam iteam = new Iteam(id,name,pgflag);
            mExampleList.add(iteam);
        }

        customAdapter = new CustomAdapter(mExampleList,getContext());
        recyclerView.setAdapter(customAdapter);
    }

    private void fetchGovtHospitalsDB(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        mExampleList.clear();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Log.i("adapter1", Integer.toString(i) + json.toString());
            int id = json.getInt("h_id");
            String name = json.getString("h_name");
            String pgflag = json.getString("h_pgflag");
            Iteam iteam = new Iteam(id,name,pgflag);
            mExampleList.add(iteam);
        }

        customAdapter = new CustomAdapter(mExampleList,getContext());
        recyclerView.setAdapter(customAdapter);
    }

    private void filter(String text) {
        ArrayList<Iteam> filteredList = new ArrayList<Iteam>();

        for (Iteam i : mExampleList) {
            if (i.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(i);
            }
        }

        customAdapter.filterList(filteredList);
    }

    public void fetchDataFromInternet() throws JSONException {
        JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
        JSONArray jsonArray = jsonObject.getJSONArray("hospitallist");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            int id = json.getInt("h_id");
            String name = json.getString("h_name");
            String pgflag = json.getString("h_pgflag");
            Iteam iteam = new Iteam(id,name,pgflag);
            mExampleList.add(iteam);
        }
        customAdapter = new CustomAdapter(mExampleList,getContext());
        recyclerView.setAdapter(customAdapter);
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("govhospital.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
