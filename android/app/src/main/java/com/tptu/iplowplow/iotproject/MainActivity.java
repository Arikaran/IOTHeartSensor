package com.tptu.iplowplow.iotproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 100;
    private static final int PERMISSIONS_REQUEST_SMS_SEND = 100;
    private static String[] PERMISSIONS_PHONECALL = {Manifest.permission.CALL_PHONE};
    public static TextView textViewBpm;
    public static TextView textViewThemperature;
    private static Context mContext;
    private static FusedLocationProviderClient mFusedLocationClient;
    public static AppCompatActivity sApplication;
    public static ArrayList<Integer> tabAlerte = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.icone_app);
        }

        sApplication = this;
        textViewBpm = (TextView) findViewById(R.id.textView1);
        textViewThemperature = (TextView) findViewById(R.id.textView2);
        mContext = this.getApplicationContext();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SMS_SEND);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
        }

        refreshConstante();
        Intent intent = new Intent(this, BackgroundService.class);
        startService(intent);
    }

    public static void refreshConstante() {

        final WebServices webServiceFiche = new WebServices("http://192.168.4.1/", new ServerCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    textViewBpm.setText(result.get("bpm").toString());
                    // textViewThemperature.setText(result.get("temperature").toString());
                    if ((int) result.get("bpm") < 20 || (int) result.get("bpm") > 170 /*|| (int)result.get("temperature")<20 || (int)result.get("temperature")>40 */) {
                        tabAlerte.add(1);
                        if(tabAlerte.size()>=3){
                            sendSms((int) result.get("bpm")/*,(int) result.get("temperature") */);
                            urgenceCall();
                        }

                    } else {
                        tabAlerte = new ArrayList<>();
                        System.out.println("SUP");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("error", "Error: " + error.getMessage());
            }
        });

    }

    public static Context getAppContext() {
        return mContext;
    }

    public static void urgenceCall() {
        TelephonyManager manager = (TelephonyManager) getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(getAppContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        }
        Intent appel = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0609246092"));
        getAppContext().startActivity(appel);
    }

    public static void sendSms(final int bpm) {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener( new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        Double latitude = 0.0;
                        Double longitude = 0.0;

                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("0609246092", null, "Bpm : "+bpm+" ; Température : ; Latitude : "+latitude+" ; Logitude :"+longitude+" ;", null, null);
                    }


                })
                .addOnFailureListener(sApplication, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.getMessage());
                    }
                })

                    ;


    }

}
