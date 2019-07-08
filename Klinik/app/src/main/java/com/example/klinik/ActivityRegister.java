package com.example.klinik;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.klinik.api.client;
import com.example.klinik.model.model_user.DataUser;
import com.example.klinik.model.model_user.ResponseRegister;
import com.example.klinik.myinterface.InitComponent;
import com.example.klinik.utils.move;
import com.example.klinik.utils.validate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import customfonts.MyTextView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityRegister extends AppCompatActivity  implements InitComponent, View.OnClickListener {

    private EditText Nama;
    private EditText NIK;
    private EditText birth;
    private EditText Alamat;
    private EditText KK;
    private MyTextView Agama;
    private MyTextView Pendidikan;
    private MyTextView Pekerjaan;
    private RadioButton Islam;
    private RadioButton Kristen;
    private RadioButton Katolik;
    private RadioButton Hindu;
    private RadioButton Budha;
    private RadioButton Konghuchu;

    private RadioButton SD;
    private RadioButton SMP;
    private RadioButton SMA;
    private RadioButton S1;
    private RadioButton S2;
    private RadioButton S3;

    private RadioButton Belum;
    private RadioButton Pelajar;
    private RadioButton PNS;
    private RadioButton Swasta;
    private RadioButton Buruh;
    private RadioButton Pensiunan;

    private EditText no_hp;
    private EditText Password;
    private EditText ConPassword;
    private String JK;
    private String AG;
    private String PD;
    private String PK;
    private RadioButton rbl;
    private RadioButton rbp;
    private Button button_regist;
    private CoordinatorLayout coordinatorLayout;

    Calendar myCalendar = Calendar.getInstance();

    //declare context
    private Context mContext;

    //declare variable
    private DataUser userData;

    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext=this;
        startInit();
        initUI();
        initValue();
        initEvent();
        //AndroidNetworking.initialize(this);
    }

    /*@Override
    public void startInit() {


    }

    @Override
    public void initToolbar() {
        getSupportActionBar().hide();
    }*/


    @Override
    public void startInit() {

    }

    /*@Override
    public void initToolbar() {

    }*/

    @Override
    public void initUI() {
        Nama=(EditText)findViewById(R.id.Nama);
        NIK=(EditText)findViewById(R.id.NIK);
        birth=(EditText)findViewById(R.id.birth);
        Alamat=(EditText)findViewById(R.id.Alamat);
        KK=(EditText)findViewById(R.id.KK);
        Agama=(MyTextView)findViewById(R.id.Agama);
        Pendidikan=(MyTextView) findViewById(R.id.Pendidikan);
        Pekerjaan=(MyTextView) findViewById(R.id.Pekerjaan);
        Islam = (RadioButton)findViewById(R.id.Islam);
        Kristen = (RadioButton)findViewById(R.id.Kristen);
        Katolik = (RadioButton)findViewById(R.id.Katolik);
        Hindu = (RadioButton)findViewById(R.id.Hindu);
        Budha = (RadioButton)findViewById(R.id.Budha);
        Konghuchu =  (RadioButton)findViewById(R.id.Konghuchu);

        SD  = (RadioButton)findViewById(R.id.SD);
        SMP = (RadioButton)findViewById(R.id.SMP);
        SMA = (RadioButton)findViewById(R.id.SMA);
        S1 = (RadioButton)findViewById(R.id.S1);
        S2 = (RadioButton)findViewById(R.id.S2);
        S3 = (RadioButton)findViewById(R.id.S3);

        Belum = (RadioButton)findViewById(R.id.Belum);
        Pelajar = (RadioButton)findViewById(R.id.Pelajar);
        PNS = (RadioButton)findViewById(R.id.PNS);
        Swasta = (RadioButton)findViewById(R.id.Swasta);
        Buruh = (RadioButton)findViewById(R.id.Buruh);
        Pensiunan = (RadioButton)findViewById(R.id.Pensiunan);

        no_hp=(EditText)findViewById(R.id.no_hp);
        Password=(EditText)findViewById(R.id.Password);
        ConPassword=(EditText)findViewById(R.id.ConPassword);
        rbl=(RadioButton)findViewById(R.id.jkl);
        rbp=(RadioButton)findViewById(R.id.jkp);
        button_regist=(Button)findViewById(R.id.button_regist);
        birth = findViewById(R.id.birth);

        birth.setFocusableInTouchMode(false);
        birth.setFocusable(false);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivityRegister.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    setBirth();
                }

            };

            private void setBirth() {
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                birth.setText(sdf.format(myCalendar.getTime()));
            }


        });
    }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        button_regist.setOnClickListener(this);
        rbl.setOnClickListener(this);
        rbp.setOnClickListener(this);

        Islam.setOnClickListener(this);
        Kristen.setOnClickListener(this);
        Katolik.setOnClickListener(this);
        Hindu.setOnClickListener(this);
        Budha.setOnClickListener(this);
        Konghuchu.setOnClickListener(this);

        SD.setOnClickListener(this);
        SMP.setOnClickListener(this);
        SMA.setOnClickListener(this);
        S1.setOnClickListener(this);
        S2.setOnClickListener(this);
        S3.setOnClickListener(this);

        Belum.setOnClickListener(this);
        Pelajar.setOnClickListener(this);
        PNS.setOnClickListener(this);
        Swasta.setOnClickListener(this);
        Buruh.setOnClickListener(this);
        Pensiunan.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_regist:
                if (validasi())
                    register();
                break;
            case R.id.jkl:
                JK = "1";
                rbp.setChecked(false);
                break;
            case R.id.jkp:
                JK = "2";
                rbl.setChecked(false);
                break;

            case R.id.Islam:
                AG = "1";
                Kristen.setChecked(false);
                Katolik.setChecked(false);
                Hindu.setChecked(false);
                Budha.setChecked(false);
                Konghuchu.setChecked(false);
                break;
            case R.id.Kristen:
                AG = "2";
                Islam.setChecked(false);
                Katolik.setChecked(false);
                Hindu.setChecked(false);
                Budha.setChecked(false);
                Konghuchu.setChecked(false);
                break;
            case R.id.Katolik:
                AG = "3";
                Islam.setChecked(false);
                Kristen.setChecked(false);
                Hindu.setChecked(false);
                Budha.setChecked(false);
                Konghuchu.setChecked(false);
                break;
            case R.id.Hindu:
                AG = "4";
                Islam.setChecked(false);
                Kristen.setChecked(false);
                Katolik.setChecked(false);
                Budha.setChecked(false);
                Konghuchu.setChecked(false);
                break;
            case R.id.Budha:
                AG = "5";
                Islam.setChecked(false);
                Kristen.setChecked(false);
                Katolik.setChecked(false);
                Hindu.setChecked(false);
                Konghuchu.setChecked(false);
                break;
            case R.id.Konghuchu:
                AG = "6";
                Islam.setChecked(false);
                Kristen.setChecked(false);
                Katolik.setChecked(false);
                Hindu.setChecked(false);
                Budha.setChecked(false);
                break;

            case R.id.SD:
                PD = "1";
                SMP.setChecked(false);
                SMA.setChecked(false);
                S1.setChecked(false);
                S2.setChecked(false);
                S3.setChecked(false);
                break;
            case R.id.SMP:
                PD = "2";
                SD.setChecked(false);
                SMA.setChecked(false);
                S1.setChecked(false);
                S2.setChecked(false);
                S3.setChecked(false);
                break;
            case R.id.SMA:
                PD = "3";
                SD.setChecked(false);
                SMP.setChecked(false);
                S1.setChecked(false);
                S2.setChecked(false);
                S3.setChecked(false);
                break;
            case R.id.S1:
                PD = "4";
                SD.setChecked(false);
                SMP.setChecked(false);
                SMA.setChecked(false);
                S2.setChecked(false);
                S3.setChecked(false);
                break;
            case R.id.S2:
                PD = "5";
                SD.setChecked(false);
                SMP.setChecked(false);
                SMA.setChecked(false);
                S1.setChecked(false);
                S3.setChecked(false);
                break;
            case R.id.S3:
                PD = "6";
                SD.setChecked(false);
                SMP.setChecked(false);
                SMA.setChecked(false);
                S1.setChecked(false);
                S2.setChecked(false);
                break;

            case R.id.Belum:
                PK = "1";
                Pelajar.setChecked(false);
                PNS.setChecked(false);
                Swasta.setChecked(false);
                Buruh.setChecked(false);
                Pensiunan.setChecked(false);
                break;
            case R.id.Pelajar:
                PK = "2";
                Belum.setChecked(false);
                PNS.setChecked(false);
                Swasta.setChecked(false);
                Buruh.setChecked(false);
                Pensiunan.setChecked(false);
                break;
            case R.id.PNS:
                PK = "3";
                Belum.setChecked(false);
                Pelajar.setChecked(false);
                Swasta.setChecked(false);
                Buruh.setChecked(false);
                Pensiunan.setChecked(false);
                break;
            case R.id.Swasta:
                PK = "4";
                Belum.setChecked(false);
                Pelajar.setChecked(false);
                PNS.setChecked(false);
                Buruh.setChecked(false);
                Pensiunan.setChecked(false);
                break;
            case R.id.Buruh:
                PK = "5";
                Belum.setChecked(false);
                Pelajar.setChecked(false);
                PNS.setChecked(false);
                Swasta.setChecked(false);
                Pensiunan.setChecked(false);
                break;
            case R.id.Pensiunan:
                PK = "6";
                Belum.setChecked(false);
                Pelajar.setChecked(false);
                PNS.setChecked(false);
                Swasta.setChecked(false);
                Buruh.setChecked(false);
                break;
        }

    }

    public void register(){
        pDialog = new ProgressDialog(this);
        // pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setMessage("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        /*AndroidNetworking.post("http://192.168.43.93/sahaduta_api/index.php/api/user/register")
                .addBodyParameter("Nama", Nama.getText().toString())
                .addBodyParameter("birth", birth.getText().toString())
                .addBodyParameter("Alamat", Alamat.getText().toString())
                .addBodyParameter("KK", KK.getText().toString())
                .addBodyParameter("Agama", Agama.getSelectedItem().toString())
                .addBodyParameter("Pendidikan", Pendidikan.getSelectedItem().toString())
                .addBodyParameter("Pekerjaan", Pekerjaan.getSelectedItem().toString())
                .addBodyParameter("no_hp", no_hp.getText().toString())
                .addBodyParameter("NIK", NIK.getText().toString())
                .addBodyParameter("Password", Password.getText().toString())
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Boolean status = response.getBoolean("status");
                            String message = response.getString("message");
                                showMessage(message);
                            if(status){
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });*/

        Call register;
        register = client.getApi().userRegister
                                (Password.getText().toString(),
                                Nama.getText().toString(),
                                birth.getText().toString(),
                                Alamat.getText().toString(),
                                KK.getText().toString(),
                                AG,
                                PD,
                                PK,
                                //Agama.getSelectedItem().toString(),
                                //Pendidikan.getSelectedItem().toString(),
                                //Pekerjaan.getSelectedItem().toString(),
                                JK,
                                no_hp.getText().toString(),
                                NIK.getText().toString()
                                );

        register.enqueue(new Callback<ResponseRegister>() {

            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                pDialog.hide();
                Log.e("Response",response.message());
                if (response.isSuccessful()){
                    if (response.body().getStatus()) {
                        Toasty.success(mContext,"Berhasil Dibuat", Toast.LENGTH_LONG).show();
                        move.moveActivity(mContext,ActivityRekam.class);
                    }else {
                        Toasty.success(mContext,"Gagal dibuat", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.error(mContext,"Gagal dibuat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Log.d("error" , ""+call);
                //Log.e("on Failure ",call.toString());
                pDialog.hide();
                Toasty.success(mContext,"Berhasil Dibuat u", Toast.LENGTH_LONG).show();
                move.moveActivity(mContext,ActivityRekam.class);
            }
        });
    }



    private Boolean validasi(){
        if (!validate.cek(Nama)
                &&!validate.cek(birth)
                &&!validate.cek(Alamat)
                &&!validate.cek(KK)
                &&!validate.cek(Agama)
                &&!validate.cek(Pendidikan)
                &&!validate.cek(Pekerjaan)
                &&!validate.cek(no_hp)
                &&!validate.cek(NIK)
                &&!validate.cek(Password)
                &&!validate.cek(ConPassword)) {
            if (validate.cekPassword(ConPassword,Password.getText().toString(),ConPassword.getText().toString())){
                return false;
            }else{
                return true;
            }
        } else{ return false; }
    }
}
