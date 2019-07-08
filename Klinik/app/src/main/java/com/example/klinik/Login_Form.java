package com.example.klinik;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.klinik.api.ApiURL;
import com.example.klinik.helper.Shared_Preferences;
import com.example.klinik.model.model_user.DataUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import customfonts.MyEditText;
import customfonts.MyTextView;

public class Login_Form extends AppCompatActivity {
    private MyEditText no_rm;
    private MyEditText password;
    private MyTextView btn_login;
    private MyTextView txt_register;
    private MyTextView txt_bantuan;
    private TextView logofont;
    private CoordinatorLayout coordinatorlayout;
    private Shared_Preferences shared_preferences;
    //declare context
    private Context mContext;

    //declate variable
    private DataUser userData;

    //declare sweet alert
    //   private SweetAlertDialog pDialog;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading....");
        password = (MyEditText) findViewById(R.id.password);
        btn_login = (MyTextView) findViewById(R.id.btn_login);
        txt_register = (MyTextView) findViewById(R.id.txt_register);
        txt_bantuan = (MyTextView) findViewById(R.id.txt_bantuan);
        logofont = (TextView) findViewById(R.id.logofont);
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        no_rm = (MyEditText) findViewById(R.id.no_rm);
        logofont.setTypeface(custom_fonts);
        shared_preferences = new Shared_Preferences(this);
        ((MyTextView) findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_login();
            }
        });

        if (shared_preferences.get_String("bio_json") != null) {
            finish();
            startActivity(new Intent(Login_Form.this, ActivityBeranda.class));

        }
    }

    void get_login(){
        pDialog.show();
        Log.e("res pos","Jalan Request");
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.POST, ApiURL.URL("Apilogin"), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pDialog.hide();
                Log.e("res pos", s);
                try {
                    final JSONObject data = new JSONObject(s);
                    if (data.getInt("status") == 200) {
                        shared_preferences.set_String("bio_json", data.getString("biodata"));
                        alert.setTitle("Login Berhasil").setMessage(data.getString("message")).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(new Intent(Login_Form.this, ActivityBeranda.class));

                            }
                        }).show();
                    } else {
                        alert.setTitle("Login Gagal").setMessage(data.getString("message")).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    alert.setTitle("Login Gagal").setMessage(e.getMessage())
                            .setPositiveButton("Coba Lagi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    get_login();
                                }
                            }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError VolleyError) {
                pDialog.hide();
                VolleyError.printStackTrace();
                alert.setTitle("Login Gagal").setMessage(VolleyError.getMessage())
                    .setPositiveButton("Coba Lagi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            get_login();
                        }
                    }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                }).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pos = new Hashtable<>();
                pos.put("no_rm", no_rm.getText().toString());
                pos.put("password", password.getText().toString());
                return pos;
            }
        });
    }
}

