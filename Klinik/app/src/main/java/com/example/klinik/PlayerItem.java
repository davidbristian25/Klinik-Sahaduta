package com.example.klinik;

import java.io.Serializable;

public class PlayerItem implements Serializable {
    String id_komentar, no_rm, Kritik, saran;

    public PlayerItem(String id_komentar, String no_rm, String Kritik, String saran ) {
        this.id_komentar = id_komentar;
        this.no_rm = no_rm;
        this.Kritik = Kritik;
        this.saran = saran;

    }


    public String getid_komentar() {
        return id_komentar;
    }

    public String getno_rm() {
        return no_rm;
    }

    public String getKritik() {
        return Kritik;
    }

    public String getsaran(){
        return saran;
    }


}