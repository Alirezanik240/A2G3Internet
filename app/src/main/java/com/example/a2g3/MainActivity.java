package com.example.a2g3;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

        public class MainActivity extends AppCompatActivity {

            TextView textview;

            @SuppressLint("MissingInflatedId")
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);


                textview = findViewById(R.id.tv_main);

                registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


            }



// این کلاس برای دسترسی اینترنت به برنامه است
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
//
                    if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                        if (getInternetStatus()) {
                            textview.setText("Intenet motasel Ast");
                        } else {
                            textview.setText("Intenet motasel Nist ");
                        }
                    }

                }
            };

            public boolean getInternetStatus() {
                ConnectivityManager contConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = contConnectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        return true;
                    } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        return true;
                    }
                } else {
                    return false;
                }
                return false;

            }


        }

