package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class First_Screen extends AppCompatActivity {

    private CardView hospital,search,bloodbank,notification,faqs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__screen2);

        hospital = (CardView) findViewById(R.id.card_hospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Screen.this,SearchHospital.class);
                startActivity(intent);
            }
        });
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Screen.this,SearchBySpeciality.class);
                startActivity(intent);
            }
        });
        bloodbank = findViewById(R.id.bloodbank);
        bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Screen.this,BloodBank.class);
                startActivity(intent);
            }
        });
        notification = findViewById(R.id.card_notybord);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Screen.this,Notification.class);
                startActivity(intent);
            }
        });
        faqs = findViewById(R.id.faqs);
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(First_Screen.this,faqs.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void update(MenuItem item) {
        startActivity(new Intent(getApplicationContext(),doupdate.class));
    }
}
