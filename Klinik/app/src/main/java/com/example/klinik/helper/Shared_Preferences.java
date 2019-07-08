package com.example.klinik.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_Preferences {
    public SharedPreferences sp;
    public  SharedPreferences.Editor spe;
    Context ctx;
    public  Shared_Preferences (Context context) {
        ctx = context;
        sp = ctx.getSharedPreferences("apa aja",0);
        spe = sp.edit();

    }
    public void set_String(String key, String value) {
        spe.putString(key,value);
        spe.commit();
    }
    public  String get_String(String key) { return sp.getString(key, null);}
}
