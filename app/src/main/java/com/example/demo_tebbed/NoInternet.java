package com.example.demo_tebbed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

public class NoInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        dia();
    }
    public void dia(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NoInternet.this);

        // Setting Dialog Title
        alertDialog.setTitle("Connectivity!...");

        // Setting Dialog Message
        alertDialog.setMessage("Please check your internet connectivity!");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setNeutralButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else {
                    connected = false;
                }
                //Toast.makeText(NoInternet.this, ""+connected, Toast.LENGTH_SHORT).show();
                if (connected==false){
                    dia();
                }
                else{
                    Intent intent = new Intent(NoInternet.this,First_Screen.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                dialog.cancel();
                finish();
                System.exit(0);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void click(View view) {
    dia();
    }

}
