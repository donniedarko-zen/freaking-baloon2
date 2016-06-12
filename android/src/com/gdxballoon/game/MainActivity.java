package com.gdxballoon.game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault);
        setContentView(R.layout.activity_main);
        final Config gameConfig = new Config(true);
        Button buton = (Button) findViewById(R.id.button);
        Button sesButon = (Button) findViewById(R.id.button2);

        Button hakkindaButon = (Button) findViewById(R.id.button4);




        sesButon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mesaj;
                gameConfig.ses ^= true;
                if(gameConfig.ses){
                    mesaj = "Ses Acildi";

                }else{
                    mesaj = "Ses Kapatildi";

                }
                Toast.makeText(getApplicationContext(), mesaj , Toast.LENGTH_SHORT).show();

            }
        });

        hakkindaButon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String names[] ={"130201010 Selman Kayrancıoğlu","130201018 İrem Özen","130201012 Buse Torunlar"};
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View convertView = inflater.inflate(R.layout.hakkinda, null);
                alertDialog.setView(convertView);
                alertDialog.setTitle("Hakkinda");
                ListView lv = (ListView) convertView.findViewById(R.id.listView1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,names);
                lv.setAdapter(adapter);
                alertDialog.show();
            }
        });


        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oyun = new Intent(v.getContext(), MapActivity.class);
                oyun.putExtra("config", gameConfig);
                startActivity(oyun);

            }


        });

        if(gameConfig.bittiMi){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Puan")
                    .setTitle("falanfilan");
            AlertDialog dialog = builder.create();

            dialog.show();
        }


    }



}
