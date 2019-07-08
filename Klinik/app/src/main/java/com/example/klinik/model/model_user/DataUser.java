package com.example.klinik.model.model_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {
  @SerializedName("no_rm")
  @Expose
  private String no_rm;

  @SerializedName("password")
  @Expose
  private String password;

  @SerializedName("nama_pasien")
  @Expose
  private String nama_pasien;

  @SerializedName("tgl_lahir")
  @Expose
  private String tgl_lahir;

  @SerializedName("alamat")
  @Expose
  private String alamat;

  @SerializedName("nama_kk")
  @Expose
  private String nama_kk;

  @SerializedName("id_agama")
  @Expose
  private String id_agama;

  @SerializedName("id_pendidikan")
  @Expose
  private String id_pendidikan;

  @SerializedName("id_pekerjaan")
  @Expose
  private String id_pekerjaan;

  @SerializedName("id_jenis_kelamin")
  @Expose
  private String id_jenis_kelamin;

  @SerializedName("no_hp")
  @Expose
  private String no_hp;

  @SerializedName("NIK")
  @Expose
  private String NIK;


  public String getNo_rm() { return no_rm; }

  public void setNo_rm(String no_rm) {
    this.no_rm = no_rm;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNamaPasien() {
    return nama_pasien;
  }

  public void setNamaPasien(String nama_pasien) {
    this.nama_pasien = nama_pasien;
  }

  public String getTglLahir() {
    return tgl_lahir;
  }

  public void setTglLahir(String tgl_lahir) {
    this.tgl_lahir = tgl_lahir;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public String getNamaKK() {
    return nama_kk;
  }

  public void setNamaKK(String nama_kk) {
    this.nama_kk = nama_kk;
  }

  public String getid_agama() {
    return id_agama;
  }

  public void setid_agama(String id_agama) {
    this.id_agama = id_agama;
  }

  public String getid_pendidikan() {
    return id_pendidikan;
  }

  public void setid_pendidikan(String id_pendidikan) {
    this.id_pendidikan = id_pendidikan;
  }

  public String getid_pekerjaan() {
    return id_pekerjaan;
  }

  public void setid_pekerjaan(String id_pekerjaan) {
    this.id_pekerjaan = id_pekerjaan;
  }

  public String getid_jenis_kelamin() {
    return id_jenis_kelamin;
  }

  public void setid_jenis_kelamin(String id_jenis_kelamin) {
    this.id_jenis_kelamin = id_jenis_kelamin;
  }

  public String getNo_hp() {
    return no_hp;
  }

  public void setNo_hp(String no_hp) {
    this.no_hp = no_hp;
  }

  public String getNIK() {
    return NIK;
  }

  public void setNIK(String NIK) { this.NIK = NIK;
  }


















}
