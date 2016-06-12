package com.gdxballoon.game;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gdxballoon.game.Balloon;

import java.util.Objects;

public class AndroidLauncher extends AndroidApplication {
    ActionResolverAndroid actionResolverAndroid;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();


        Intent intent = getIntent();
        Config gameConfig = (Config) intent.getExtras().getSerializable("config");

        actionResolverAndroid = new ActionResolverAndroid(this);

        initialize(new Balloon(gameConfig, actionResolverAndroid), config);


        /*

        if(gameConfig.bitti){

            startActivity(new Intent(this, MainActivity.class));
            if (Objects.equals(gameConfig.icerde, "icerde")) {
                Toast.makeText(this, "works fucker", Toast.LENGTH_LONG).show();
            }
        }




*/
    }
}
