package com.luwakode.tajwid.server;

public class ServerApi {
    private static final String site_url = "https://tajwid.luwakode.com/api/";
    private static final String web_url = "https://tajwid.luwakode.com/webview/#/app/";

    public static final String login = site_url+"login.php";
    public static final String daftar = site_url+"buat-akun.php";
    public static final String profil = site_url+"profil.php?id=";

    public  static final String materi = web_url+"materi";
    public  static final String definisi = web_url+"definisi";
    public  static final String soal = web_url+"soal";
    public  static final String chat = web_url+"chat/";
}
