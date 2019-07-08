package com.example.klinik.model.mode_antrian;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AntrianSekarang {
    @SerializedName("no_rm")
    @Expose
    private String no_rm;

    public String getno_rm() {
        return no_rm;
    }

    public void setno_rm() {
        this.no_rm = no_rm;
    }
}
