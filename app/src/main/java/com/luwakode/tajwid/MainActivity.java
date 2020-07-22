package com.luwakode.tajwid;

import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.luwakode.tajwid.activity.BaseActivity;
import com.luwakode.tajwid.activity.Profil;
import com.luwakode.tajwid.activity.WebView;
import com.luwakode.tajwid.server.ServerApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    CardView card_materi, card_definisi, card_soal, card_chat, card_tentang, card_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        findView();
        initView();
        initListener();
    }

    @Override
    public void findView() {
        card_materi = findViewById(R.id.card_materi);
        card_definisi = findViewById(R.id.card_definisi);
        card_soal = findViewById(R.id.card_soal);
        card_chat = findViewById(R.id.card_chat);
        card_tentang = findViewById(R.id.card_tentang);
        card_profil = findViewById(R.id.card_profil);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        card_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WebView.class);
                i.putExtra("title", "Materi Tajwid");
                i.putExtra("url", ServerApi.materi);
                startActivity(i);
            }
        });
        card_definisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WebView.class);
                i.putExtra("title", "Definisi Tajwid");
                i.putExtra("url", ServerApi.definisi);
                startActivity(i);
            }
        });
        card_soal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WebView.class);
                i.putExtra("title", "Soal");
                i.putExtra("url", ServerApi.soal);
                startActivity(i);
            }
        });
        card_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WebView.class);
                i.putExtra("title", "Chat");
                i.putExtra("url", ServerApi.chat);
                startActivity(i);
            }
        });
        card_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.tentang)
                        .setTitle("Tentang")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setNegativeButton("Ok", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        card_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Profil.class);
                startActivity(i);
            }
        });
    }
}