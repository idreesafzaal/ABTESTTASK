package e.visiontech.testab.VIEWS;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import e.visiontech.testab.DATAMODELS.ExpDataModel;
import e.visiontech.testab.NETWORKS.NetworksCall;
import e.visiontech.testab.R;
import e.visiontech.testab.STATS.Routes;
import e.visiontech.testab.STATS.Utilies;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String manufacturer = Build.MANUFACTURER;
    String model = Build.MODEL;
    int version = Build.VERSION.SDK_INT;
    String versionRelease = Build.VERSION.RELEASE;
    NetworksCall networksCall;
    Utilies utilies;
    Button Signup,Lateron,StatBtn;
    List<ExpDataModel> expDataModelList;
    SharedPreferences sharedPref;
    String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("application", this.MODE_PRIVATE);
        setview();
        utilies = new Utilies(MainActivity.this);
        networksCall = new NetworksCall(MainActivity.this);

        //call to get list of experiments using volley and url
        networksCall.getExpList(utilies.getDeviceId());
        String color=sharedPref.getString("color","");

        //here just check the device have assgin colour before and also set probility 75,20 and 5
        setExpToView(color);
       // Log.d("expoData",expDataModelList.get(1).getExpPercentage());



    }

    private void setview()
    {
        Signup=findViewById(R.id.Singup);
        Lateron=findViewById(R.id.Guest);
        StatBtn=findViewById(R.id.StatsBtn);
        StatBtn.setVisibility(View.INVISIBLE);

        Signup.setOnClickListener(this);
        Lateron.setOnClickListener(this);
        StatBtn.setOnClickListener(this);
    }

  public void setExpToView(String color)
    {



        if(color.equals("")) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            expDataModelList = Arrays.asList(gson.fromJson(sharedPref.getString("data", ""), ExpDataModel[].class));

            // assgin random probility according to %
            Random rand = new Random();
            double r = rand.nextDouble() * 100;
            Log.d("color", String.valueOf(r));
            if (r <= 75 && r > 20 || r <= 100 && r > 75) {


                Signup.setBackgroundColor(Color.parseColor(expDataModelList.get(0).getExpNum()));
                Lateron.setBackgroundColor(Color.parseColor(expDataModelList.get(0).getExpNum()));
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("color", expDataModelList.get(0).getExpNum());
                editor.putString("ExpId", expDataModelList.get(0).getExpId());
                editor.apply();
                // <-- 75% of the time.
            } else if (r < 20 && r > 5) {
                Signup.setBackgroundColor(Color.parseColor(expDataModelList.get(1).getExpNum()));
                Lateron.setBackgroundColor(Color.parseColor(expDataModelList.get(1).getExpNum()));
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("color", expDataModelList.get(1).getExpNum());
                editor.putString("ExpId", expDataModelList.get(1).getExpId());
                editor.apply();
            } else if (r <= 5) {
                Signup.setBackgroundColor(Color.parseColor(expDataModelList.get(2).getExpNum()));
                Lateron.setBackgroundColor(Color.parseColor(expDataModelList.get(2).getExpNum()));
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("color", expDataModelList.get(2).getExpNum());
                editor.putString("ExpId", expDataModelList.get(2).getExpId());

                editor.apply();
            }
        }else
            {
                Signup.setBackgroundColor(Color.parseColor(color));
                Lateron.setBackgroundColor(Color.parseColor(color));
            }
    }


    @Override
    public void onClick(View view)
    {

        switch (view.getId()) {

            case R.id.Singup:


// here check the and enter the hit for expermient when user click
                 getStats();
                break;
            case R.id.Guest:



                    getStats();

                break;
            case R.id.StatsBtn:
                Intent intent=new Intent(MainActivity.this,StatsActivity.class);
                startActivity(intent);
                break;



        }
    }

    private void getStats()
    {
        String check=sharedPref.getString("ExpId","");
        if(!check.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, new Routes().GetStatsList, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("idress", response);
                    SharedPreferences sharedPref = getSharedPreferences("applicationdata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("response", response);
                    editor.apply();
                    StatBtn.setVisibility(View.VISIBLE);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("idres", error.getMessage());
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("ExID", sharedPref.getString("ExpId", ""));
                    MyData.put("DeviceId", utilies.getDeviceId());
                    MyData.put("Count", "1");
                    return MyData;
                }

            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue rQueue = Volley.newRequestQueue(this);
            rQueue.add(stringRequest);
        }
    }
}
