package com.example.klinik.SPreferenced;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */

public class SPref {
  private static String no_rm="no_rm";
  private static String NIK="NIK";
  private static String tgl_lahir="tgl_lahir";
  private static String nama_pasien="nama_pasien";
  private static String nama_kk="nama_kk";
  private static String pendidikan="pendidikan";
  private static String pekerjaan="pekerjaan";
  private static String no_hp="no_hp";
  private static String jenis_kelamin="jenis_kelamin";
  private static String alamat="alamat";
  //private static String LAST_UPDATE="LAST_UPDATE";
  private static String photo="photo";
  private static String group_user="group_user";
  private static String PASSWORD="PASSWORD";
  private static String CARTS="CARTS";



  public static String getPassword() {
    return PASSWORD;
  }

  public static void setPassword(String PASSWORD) { SPref.PASSWORD = PASSWORD;
  }

  public static String getNo_rm() {
    return no_rm;
  }

  public static String getNamaPasien() {
    return nama_pasien;
  }

  public static String getTglLahir() {
    return tgl_lahir;
  }

  public static String getNik() {
    return NIK;
  }

  public static String getNamaKK() {
    return nama_kk;
  }

  public static String getPendidikan() {
    return pendidikan;
  }


  public static String getPekerjaan() {
    return pekerjaan;
  }

  public static String getNo_hp() {
    return no_hp;
  }


  public static String getJenisKelamin() {
    return jenis_kelamin;
  }

  public static String getAlamat() {
    return alamat;
  }

  public static String getPhoto() {
    return photo;
  }

  public static String getGroupUser() {
    return group_user;
  }

    /*public static String getLastUpdate() {
        return LAST_UPDATE;
    }

    public static String getALAMAT() {
        return ALAMAT;
    }*/


}
