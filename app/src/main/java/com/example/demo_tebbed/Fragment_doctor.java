package com.example.demo_tebbed;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Fragment_doctor extends Fragment {

    int id = Next_scree_Update.h_id;
    DoctorAdapter doctorAdapter;
    RecyclerView recyclerView;
    SqliteDatabse sqliteDatabse;
    ArrayList<Doctor> doctorList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_doctor,null);

        sqliteDatabse = new SqliteDatabse(getContext());
        String doctors = sqliteDatabse.showDoctorName(id);
        Log.i("doctorsString", doctors);

        doctorList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(doctors);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Log.i("adapter1", Integer.toString(i) + json.toString());
                int id = json.getInt("d_id");
                String name = json.getString("d_name");
                Doctor doctor = new Doctor(id, name);
                doctorList.add(doctor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




        recyclerView = view.findViewById(R.id.recyclerDoctor);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        doctorAdapter = new DoctorAdapter(doctorList, getContext());
        recyclerView.setAdapter(doctorAdapter);


        return view;

    }


}
