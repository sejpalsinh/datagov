package com.example.demo_tebbed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Fragment_Hospital extends Fragment {
    TextView hname,adderess,num, web, loc, time, email;
    SqliteDatabse sqliteDatabse;
    String h_name;
    String h_add, h_number, h_email, h_website, h_locaion, h_time;
    int id = Next_scree_Update.h_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment__hospital,null);

        //Toast.makeText(getActivity(), String.valueOf(id), Toast.LENGTH_LONG).show();
        Log.i("stast", String.valueOf(id));
        hname = view.findViewById(R.id.hname);
        adderess = view.findViewById(R.id.hadd);
        num = view.findViewById(R.id.hnumber);
        web = view.findViewById(R.id.hwebsite);
        loc = view.findViewById(R.id.hlocationlink);
        email = view.findViewById(R.id.hemail);
        time = view.findViewById(R.id.htime);
        sqliteDatabse = new SqliteDatabse(getActivity());
        String result = sqliteDatabse.showHospi(id);

        try {
            fillHospitalsFromDB(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;

    }
    private void fillHospitalsFromDB(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            h_name = json.getString("h_name");
            h_add= json.getString("h_address");
            h_number = json.getString("h_number");
            h_email = json.getString("h_email");
            h_website = json.getString("h_website");
            h_locaion = json.getString("h_location");
            h_time = json.getString("h_time");
        }
        hname.setText(h_name);
        adderess.setText(h_add);
        num.setText(h_number);
        web.setText(h_website);
        loc.setText(h_locaion);
        time.setText(h_time);
        email.setText(h_email);
    }
}
