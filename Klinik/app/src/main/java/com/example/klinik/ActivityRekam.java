package com.example.klinik;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.klinik.model.model_rm.DataRM;
import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import customfonts.MyTextView;

public class ActivityRekam extends AppCompatActivity implements InitComponent, View.OnClickListener {

  private DataRM RM;
  private TextView TampilRM;
  private MyTextView btn_lanjut;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

  ArrayList<HashMap<String, String>> list_data;

  private Context mContext;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rekam);
    startInit();
    mContext=this;

    String url = "http://192.168.43.93/Android/getdata.php"; //sesuaikan dengan ip pc anda
    TampilRM = (TextView)findViewById(R.id.TampilRM);

    requestQueue = Volley.newRequestQueue(ActivityRekam.this);

    list_data = new ArrayList<HashMap<String, String>>();

    stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          JSONObject jsonResponse = new JSONObject(response);
          JSONArray jsonArray = jsonResponse.getJSONArray("no_rm");
          for (int a = 0; a < jsonArray.length(); a ++){
            JSONObject json = jsonArray.getJSONObject(a);
            HashMap<String, String> map  = new HashMap<String, String>();
            map.put("no_rm", json.getString("no_rm"));
            list_data.add(map);
          }
          TampilRM.setText(list_data.get(0).get("no_rm"));
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(ActivityRekam.this, error.getMessage(), Toast.LENGTH_LONG).show();
      }
    });

    requestQueue.add(stringRequest);
  }

  @Override
  public void startInit() {
    //initToolbar();
    initUI();
    initValue();
    initEvent();
  }

  @Override
  public void initUI() {
    btn_lanjut=(MyTextView) findViewById(R.id.btn_lanjut);

  }

  @Override
  public void initEvent() {
    btn_lanjut.setOnClickListener(this);

  }

  @Override
  public void initValue() {

  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){

      case R.id.btn_lanjut:
        move.moveActivity(mContext,ActivityBeranda.class);
        break;

    }
  }
}