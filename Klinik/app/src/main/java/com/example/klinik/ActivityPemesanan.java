package com.example.klinik;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.klinik.helper.SharedPrefManager;
import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityPemesanan extends AppCompatActivity implements InitComponent, View.OnClickListener {

    Dialog mydialog;
    private TextView NomorPemesanan;
    private Button btnPesan;

    private TextView RMpemesanan;

    private SharedPrefManager sharedPrefManager;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        mydialog=new Dialog(this);
        startInit();
        mContext=this;

        /**ANTRIAN**/
        String url = "http://192.168.43.93/sahaduta/getAntrianBaru.php"; //sesuaikan dengan ip pc anda
        NomorPemesanan = (TextView)findViewById(R.id.NomorPemesanan);
        requestQueue = Volley.newRequestQueue(ActivityPemesanan.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("no_antrian");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("no_antrian", json.getString("no_antrian"));
                        list_data.add(map);
                    }
                    NomorPemesanan.setText(list_data.get(0).get("no_antrian"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityPemesanan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);

        /**Menampilkan NoRM**/
        RMpemesanan = (TextView) findViewById(R.id.RMpemesanan);
        //RMpemesanan.setText(SharedPrefManager.saveSPString(SP_NO_RM);

        RMpemesanan.setText(sharedPrefManager.getSPNo_rm());
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
        btnPesan =(Button) findViewById(R.id.btnPesan);

    }

    @Override
    public void initEvent() {
        btnPesan.setOnClickListener(this);

    }

    @Override
    public void initValue() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnPesan:
                move.moveActivity(mContext,ActivityBeranda.class);
                break;

        }
    }
}