package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SearchHospital extends AppCompatActivity {
    Spinner spnState, spnDistrict, spnCity;
    CheckBox chkAllSearch;
    List<String> allStates, allDistricts;
    Button btnSearch;
    JSONObject jsonAllStatesInfo;
    JSONArray arrAllStates;
    ArrayAdapter<String> adapterStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital);


        spnState = findViewById(R.id.spnState);
        spnDistrict = findViewById(R.id.spnDistrict);
        allStates = new ArrayList<>();
        allDistricts = new ArrayList<>();

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchHospital = new Intent(SearchHospital.this, MainActivity.class);
                searchHospital.putExtra("all", chkAllSearch.isChecked());
                searchHospital.putExtra("state", spnState.getSelectedItem().toString());
                searchHospital.putExtra("district", spnDistrict.getSelectedItem().toString());

            }
        });


        chkAllSearch = findViewById(R.id.chkAllSearch);
        chkAllSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    spnDistrict.setEnabled(false);
                    spnDistrict.setClickable(false);
                    spnState.setEnabled(false);
                    spnState.setClickable(false);

                    if(!btnSearch.isEnabled()){
                        btnSearch.setEnabled(true);
                    }
                } else {
                    spnState.setEnabled(true);
                    spnState.setClickable(true);

                    if(spnState.getSelectedItemPosition() != 0){
                        spnDistrict.setEnabled(true);
                        spnDistrict.setClickable(true);
                        btnSearch.setEnabled(true);
                    } else {
                        btnSearch.setEnabled(false);
                    }
                }
            }
        });


        fillStatesSpinner(fillStatesJSON());


        //set on change listener on states
        spnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String getState = spnState.getSelectedItem().toString();
                JSONArray lstDistricts = null;
                List<String> updateDistrict = new ArrayList<>();

                if(spnState.getSelectedItemPosition() == 0){
                    spnDistrict.setEnabled(false);
                    spnDistrict.setClickable(false);
                    btnSearch.setEnabled(false);

                    if(chkAllSearch.isChecked()){
                        return;
                    }
                } else {
                    spnDistrict.setEnabled(true);
                    spnDistrict.setClickable(true);
                    btnSearch.setEnabled(true);
                }

                try {

                    Log.i("getPos", String.valueOf(spnState.getSelectedItemPosition()));
                    lstDistricts = arrAllStates
                            .getJSONObject(spnState.getSelectedItemPosition())
                            .getJSONArray("districts");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int j = 0; j < lstDistricts.length(); j++){
                    try {
                        updateDistrict.add(lstDistricts.getString(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(SearchHospital.this, R.layout.support_simple_spinner_dropdown_item, updateDistrict);
                spnDistrict.setAdapter(adapterDistrict);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillStatesSpinner(String allStatesInfo) {
        //Set states adapter
        try {
            jsonAllStatesInfo = new JSONObject(allStatesInfo);
            arrAllStates = jsonAllStatesInfo.getJSONArray("states");


            for(int i = 0; i < arrAllStates.length(); i++){
                JSONObject object = arrAllStates.getJSONObject(i);
                String oneState = object.getString("state");
                Log.i("state", oneState);
                allStates.add(oneState);
            }

            adapterStates = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, allStates);
            spnState.setAdapter(adapterStates);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String fillStatesJSON() {
        //Open file
        String allStatesInfo = null;
        try {
            InputStream is = this.getAssets().open("states-and-districts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            allStatesInfo = new String(buffer, "UTF-8");
            return allStatesInfo;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
