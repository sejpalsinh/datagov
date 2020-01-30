package com.example.demo_tebbed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Fragment_facilities extends Fragment {
    TextView fname,facilities;
    int id = Next_scree_Update.h_id;
    SqliteDatabse sqliteDatabse;
    String f_name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_facilities,null);

        Toast.makeText(getActivity(), String.valueOf(id), Toast.LENGTH_LONG).show();
        fname = view.findViewById(R.id.fshow);
        facilities = view.findViewById(R.id.flist);

        fname.setText("Facilities");
        sqliteDatabse = new SqliteDatabse(getActivity());
        String result = sqliteDatabse.showFacilities(id);
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
           f_name = f_name +" \n" + json.getString("f_name");
        }
        facilities.setText(f_name);
    }

}
