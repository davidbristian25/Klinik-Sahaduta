package com.example.klinik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KomentarActivity extends AppCompatActivity {

    private EditText no_rm, kritik, saran;
    private Button btn_komentari;
    private ProgressBar loading;
    private static String URL_KOMENT = "http://192.168.43.237/crud_ki/tambahkomentar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komentar);

        loading = findViewById(R.id.loading);
        no_rm = findViewById(R.id.no_rm);
        kritik = findViewById(R.id.kritik);
        saran = findViewById(R.id.saran);
        btn_komentari = findViewById(R.id.btn_komentari);

        btn_komentari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Koment();
            }
        });
    }

    private void Koment(){
        loading.setVisibility(View.VISIBLE);
        btn_komentari.setVisibility(View.GONE);

        final String no_rm = this.no_rm.getText().toString().trim();
        final String kritik = this.kritik.getText().toString().trim();
        final  String saran = this.saran.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_KOMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(KomentarActivity.this, "Berhasil mengirim komentar !", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(KomentarActivity.this, "gagal komentar !" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_komentari.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KomentarActivity.this, "gagal komentar !" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_komentari.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("no_rm", no_rm);
                params.put("kritik", kritik);
                params.put("saran", saran);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
