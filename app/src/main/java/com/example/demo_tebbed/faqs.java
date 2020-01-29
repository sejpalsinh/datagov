package com.example.demo_tebbed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class faqs extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
    textView = findViewById(R.id.data);
    String str = "You can find Hospital Details easily ."+"\n\n";
     str =str + "You will get each and every notification of hospital and related to doctors on time."+"\n\n";
     str = str + "The Address is given manually as well as we have provided google map services so , anyone can find their respective hospital easily. The person can also find , what faclities are given by the hospital here."+"\n\n";
     str = str +"The communication becomes easy as we have provided the contact information of each and every hospitals , so the patient can direct contact with hospital as well as we have provided doctor's email address."+"\n\n";
     str = str + "Health tips are given by the experts and are regularly updated."+"\n\n";
        textView.setText(str);
    }
}
