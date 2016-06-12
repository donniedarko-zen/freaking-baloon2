package com.gdxballoon.game;

import java.io.Serializable;

/**
 * Created by selman on 17.05.2016.
 */

public class Config implements Serializable {
    int score;
    boolean bittiMi;
    boolean ses;
    String arkaPlan;

    Config(boolean ses){
        this.ses = ses;
        this.score = 0;
    }
}
