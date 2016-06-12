package com.gdxballoon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by selman on 16.05.2016.
 */
public class Balon extends Rectangle {
    public long dropTime;
    public String renk;
    Balon(String renk) {
        if (renk.equals("sari")) {

            this.x = MathUtils.random(0, 480 - 64);
            this.y = MathUtils.random(0, 800 - 64);
        } else if (renk.equals("kirmizi")) {
            this.x = MathUtils.random(0, 480 - 64);
            this.y = 0;
        }else if (renk.equals("yesil")) {

            this.x = MathUtils.random(0, 480 - 64);
            this.y = 0;
        }
        else if (renk.equals("siyah")) {

            this.x = MathUtils.random(0, 480 - 64);
            this.y = 0;
        }

        this.renk = renk;
        this.width = 64;
        this.height = 64;
        this.dropTime = TimeUtils.nanoTime();
    }
}
