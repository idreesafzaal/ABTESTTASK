package e.visiontech.testab.VIEWS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import e.visiontech.testab.DATAMODELS.ExpDataModel;
import e.visiontech.testab.DATAMODELS.StatDataModel;
import e.visiontech.testab.R;

public class StatsActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    List<StatDataModel> list=new ArrayList<>();
    TextView tvDevice,tvExp1,tvExp2,tvExp3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setView();
    }

    private void setView()
    {
        tvDevice=findViewById(R.id.tvDeviceNum);
        tvExp1=findViewById(R.id.tvexpOne);
        tvExp2=findViewById(R.id.tvexpTwo);
        tvExp3=findViewById(R.id.tvexpThree);

        sharedPref = getSharedPreferences("applicationdata", this.MODE_PRIVATE);
        String data=sharedPref.getString("response","");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        list =Arrays.asList(gson.fromJson(data, StatDataModel[].class));

        tvDevice.setText("Number Of Devices : "+list.get(0).getTotalDevice());
        tvExp1.setText("Exp one hits : "+list.get(0).getExOneLike());
        tvExp2.setText("Exp two hits : "+list.get(0).getExTwoLike());
        tvExp3.setText("Exp three hits : "+list.get(0).getExThreeLike());

    }
}