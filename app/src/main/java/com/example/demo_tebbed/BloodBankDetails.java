package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BloodBankDetails extends AppCompatActivity {
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_details);

        t = findViewById(R.id.bt);
        Intent intent = getIntent();
        String s = intent.getStringExtra("b_id");
        int id = Integer.parseInt(s);
        t.setText("helo  "+ id);

    }
}
