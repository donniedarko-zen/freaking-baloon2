package com.gdxballoon.game;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MapActivity extends Activity implements AdapterView.OnItemSelectedListener {
    ImageView iv;
    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTheme(android.R.style.Theme_DeviceDefault);


        Intent intent = getIntent();
        this.config = (Config) intent.getExtras().getSerializable("config");

        Button btn = (Button) findViewById(R.id.button3);

        iv = (ImageView) findViewById(R.id.imageView);

        iv.setImageBitmap(getBitmapFromAssets("bg1.png"));
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), config.arkaPlan, Toast.LENGTH_SHORT).show();

                Intent oyun = new Intent(v.getContext(), AndroidLauncher.class);
                oyun.putExtra("config", config);
                startActivity(oyun);

            }
        });


    }

    //http://android--code.blogspot.com.tr/2015/08/android-imageview-set-image-from-assets.html
    private Bitmap getBitmapFromAssets(String fileName) {
        /*
            AssetManager
                Provides access to an application's raw asset files.
        */

        /*
            public final AssetManager getAssets ()
                Retrieve underlying AssetManager storage for these resources.
        */
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            /*
                public final InputStream open (String fileName)
                    Open an asset using ACCESS_STREAMING mode. This provides access to files that
                    have been bundled with an application as assets -- that is,
                    files placed in to the "assets" directory.

                    Parameters
                        fileName : The name of the asset to open. This name can be hierarchical.
                    Throws
                        IOException
            */
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
            BitmapFactory
                Creates Bitmap objects from various sources, including files, streams, and byte-arrays.
        */

        /*
            public static Bitmap decodeStream (InputStream is)
                Decode an input stream into a bitmap. If the input stream is null, or cannot
                be used to decode a bitmap, the function returns null. The stream's
                position will be where ever it was after the encoded data was read.

                Parameters
                    is : The input stream that holds the raw data to be decoded into a bitmap.
                Returns
                    The decoded bitmap, or null if the image data could not be decoded.
        */
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String test = (String) parent.getItemAtPosition(position);
        if(Objects.equals(test, "1")){
            iv.setImageBitmap(getBitmapFromAssets("bg1.png"));
            this.config.arkaPlan = "bg1.png";
        }else if(Objects.equals(test, "2")){
            this.iv.setImageBitmap(getBitmapFromAssets("bg2.png"));
            this.config.arkaPlan = "bg2.png";
        }else if(Objects.equals(test, "3")){
            iv.setImageBitmap(getBitmapFromAssets("bg3.png"));
            this.config.arkaPlan = "bg3.png";
        }


        //Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
