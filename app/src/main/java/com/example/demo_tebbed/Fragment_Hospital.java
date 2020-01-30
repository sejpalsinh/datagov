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
import android.widget.Toast;


public class Fragment_Hospital extends Fragment {

    int id = Next_scree_Update.h_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment__hospital,null);

        Toast.makeText(getActivity(), String.valueOf(id), Toast.LENGTH_LONG).show();
        Log.i("stast", String.valueOf(id));





        return view;

    }
}
