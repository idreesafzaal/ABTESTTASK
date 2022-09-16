package e.visiontech.testab.NETWORKS;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

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

import e.visiontech.testab.DATAMODELS.ExpDataModel;
import e.visiontech.testab.STATS.Routes;
import e.visiontech.testab.VIEWS.MainActivity;

public class NetworksCall
{
    Context context;
    List<ExpDataModel> expDataModelList;
    public NetworksCall(Context context)
    {
       this.context=context;
    }
     public void getExpList(String device_token)
     {
         StringRequest stringRequest=new StringRequest(Request.Method.POST,new Routes().GetExpList, new Response.Listener<String>() {
             @Override
             public void onResponse(String response)
             {
                 Log.d("hanan",response);
               //  Gson gson=new Gson();

//                 ExpDataModel expDataModel=new ExpDataModel();
//                 expDataModel=gson.fromJson(response,ExpDataModel.class);
               //  expDataModelList.add(expDataModel);

                 SharedPreferences sharedPref =context.getSharedPreferences("application", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = sharedPref.edit();
                 editor.putString("data", response.toString());
                 editor.apply();




             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error)
             {
                 Log.d("idres",error.getMessage());
             }
         }){
             @Nullable
             @Override
             protected Map<String, String> getParams() throws AuthFailureError
             {
                 Map<String, String> MyData = new HashMap<String, String>();
                 MyData.put("device_token", device_token);
                 return MyData;
             }

         };
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         RequestQueue rQueue = Volley.newRequestQueue(context);
         rQueue.add(stringRequest);


     }


}
