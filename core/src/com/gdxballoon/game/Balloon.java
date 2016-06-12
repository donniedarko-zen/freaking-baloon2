package com.gdxballoon.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

//import java.awt.Rectangle;
import java.sql.Time;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;


public class Balloon extends ApplicationAdapter {

    //gerekli deðiþkenler
    //private Texture kova;
    private OrthographicCamera kamera;
    private Vector3 touchPos;
    private SpriteBatch batch;
    private Sprite sprite;

    private Texture rsmRedBalon;
    private long sonKirmiziDamlaZamani;

    private Texture rsmYellowBalon;
    private long sonSariZamani;

    private Array<Balon> balonListesi;

    private BitmapFont scoreFont;
    String scoreString;
    int score;
    int toplamScore;

    private long sonYesilZamani;
    private long sonSiyahZamani;
    private Texture rsmGreenBalon;
    private Texture rsmBlackBalon;

    private BitmapFont zamanFont;
    private String zamanString;
    private long oyunZamani;

    private BitmapFont seviyeFont;
    private String seviyeString;
    private int seviye;

    private Music patlamaSes;
    private Music arkaplanSes;

    private Config ayarlar;
    public ActionResolver actionResolver;

    public boolean paused = false;
    private Texture background;




    Balloon(Config gameConfig, ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        this.ayarlar = gameConfig;
    }

    @Override
    public void create() {  //oyunun iþk çaliþtiðinda gerekli müzikleri resimleri oluþturu
        //tanimladiðimiz deðerleri çaðiriyourz
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();


        zamanFont = new BitmapFont();
        zamanFont.getData().setScale(1.5f, 1.5f);
        zamanString = "Zaman: ";
        oyunZamani = TimeUtils.nanoTime();


        scoreFont = new BitmapFont();
        scoreFont.getData().setScale(1.5f, 1.5f);
        scoreString = "Puan: ";
        score = 0;
        int toplamScore = 0;

        seviyeFont = new BitmapFont();
        seviyeFont.getData().setScale(1.5f, 1.5f);
        seviyeString = "Seviye: ";
        seviye = 1;

        patlamaSes = Gdx.audio.newMusic(Gdx.files.internal("blop.mp3"));
        arkaplanSes = Gdx.audio.newMusic(Gdx.files.internal("nyancat.mp3"));
        arkaplanSes.setLooping(true);
        arkaplanSes.play();

        if (!this.ayarlar.ses) {
            patlamaSes.setVolume(0);
            arkaplanSes.setVolume(0);
        }


        balonListesi = new Array<Balon>();
        int deviceWidth = 480;
        int devideHeight = 800;

        //kamera
        touchPos = new Vector3();
        kamera = new OrthographicCamera();
        kamera.setToOrtho(false, deviceWidth, devideHeight);

        //resim
        rsmRedBalon = new Texture(Gdx.files.internal("balloon_red.png"));
        rsmYellowBalon = new Texture(Gdx.files.internal("balloon_yellow.png"));
        rsmGreenBalon = new Texture(Gdx.files.internal("balloon_green.png"));
        rsmBlackBalon = new Texture(Gdx.files.internal("balloon_black.png"));



        background = new Texture(Gdx.files.internal(this.ayarlar.arkaPlan));


        //tuali çaðiriyourz SpriteBatch
        batch = new SpriteBatch();

        //redBalonUret();
        //yellowBalonUret();


    }

    private void yellowBalonUret() {
        balonListesi.add(new Balon("sari"));

        sonSariZamani = TimeUtils.nanoTime();
    }

    private void redBalonUret() {
        balonListesi.add(new Balon("kirmizi"));

        sonKirmiziDamlaZamani = TimeUtils.nanoTime();

    }

    private void greenBalonUret() {
        balonListesi.add(new Balon("yesil"));
        sonYesilZamani = TimeUtils.nanoTime();

    }

    private void blackBalonUret() {
        balonListesi.add(new Balon("siyah"));

        sonSiyahZamani = TimeUtils.nanoTime();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {  //içe aktardiðin resimleri müzikleri oyun çaliþtiði müddetçe gösterir
        //oyun çaliþtiði sürece çaliþir


        //ekrani boyadik
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);  //arka planin  rengi
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //kamera günceleme
        kamera.update();


        //sayfayi ayarlamak için
        batch.setProjectionMatrix(kamera.combined);

        //tual baþlatilmali
        batch.begin();
        //scoreFont.draw(batch, "AYEYEAYEA", 0, 22);
        batch.draw(background, 0, 0, 480, 800);


        scoreFont.draw(batch, scoreString + Integer.toString(score), 0, 22);
        scoreFont.draw(batch, "Toplam Puan: " + Integer.toString(toplamScore), 0, 50);
        zamanFont.draw(batch, zamanString + Long.toString((30 - TimeUtils.timeSinceNanos(oyunZamani) / 1000000000)), 200, 22);
        seviyeFont.draw(batch, seviyeString + Integer.toString(seviye), 350, 22);


        for (Balon balon : balonListesi) {
            if (balon.renk.equals("kirmizi")) {
                batch.draw(rsmRedBalon, balon.x, balon.y, 80, 100);
            } else if (balon.renk.equals("sari")) {
                batch.draw(rsmYellowBalon, balon.x, balon.y, 80, 100);
            } else if (balon.renk.equals("yesil")) {
                batch.draw(rsmGreenBalon, balon.x, balon.y, 80, 100);
            } else if (balon.renk.equals("siyah")) {
                batch.draw(rsmBlackBalon, balon.x, balon.y, 80, 100);
            }

        }

        batch.end();


        //burada balonlari patlatma olayini yapabilirsin

        //balon ne zaman üretilecek -> son damlama zamanindan 1 saniye geçmiþse damla üretilsin
        if (TimeUtils.nanoTime() - sonKirmiziDamlaZamani > 1000000000) {
            redBalonUret();
        }

        if (TimeUtils.timeSinceNanos(sonSariZamani) > 1000000000) {
            yellowBalonUret();
        }

        if (TimeUtils.timeSinceNanos(sonYesilZamani) > 1000000000) {
            greenBalonUret();
        }

        if (TimeUtils.timeSinceNanos(sonSiyahZamani) > 1000000000) {
            blackBalonUret();
        }

        Iterator<Balon> balonIterator = balonListesi.iterator();
        while (balonIterator.hasNext()) {
            Balon balon = balonIterator.next();

            if (balon.renk.equals("kirmizi")) {
                balon.y += 200 * Gdx.graphics.getDeltaTime();
            } else if (balon.renk.equals("sari")) {
                if (TimeUtils.timeSinceNanos(balon.dropTime) >= 1000000000) {
                    balonIterator.remove();
                }
            } else if (balon.renk.equals("yesil")) {

                if (TimeUtils.timeSinceNanos(balon.dropTime) >= randInt(10, 20) * 100000000) {
                    balon.renk = "siyah";
                    balon.dropTime = TimeUtils.nanoTime();
                }
                balon.y += 400 * Gdx.graphics.getDeltaTime();
            } else if (balon.renk.equals("siyah")) {
                if (TimeUtils.timeSinceNanos(balon.dropTime) >= randInt(10, 20) * 100000000) {
                    balon.renk = "yesil";
                    balon.dropTime = TimeUtils.nanoTime();
                }

                balon.y += 100 * Gdx.graphics.getDeltaTime();
            }


            if (Gdx.input.isTouched()) {
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                kamera.unproject(touchPos);

                if (balon.contains(touchPos.x, touchPos.y)) {
                    if (balon.renk.equals("kirmizi")) {
                        score += 10;
                    } else if (balon.renk.equals("sari")) {
                        score += 20;
                    } else if(balon.renk.equals("yesil")){
                        score +=5;
                    } else if(balon.renk.equals("siyah")){
                        score -=10;
                    }

                    patlamaSes.stop();
                    patlamaSes.play();
                    balonIterator.remove();
                }
            }
        }


        if (TimeUtils.timeSinceNanos(oyunZamani) / 1000000000 == 30) {
            if (score >= 100) {
                toplamScore += score;
                score = 0;
                seviye++;
                oyunZamani = TimeUtils.nanoTime();
            } else {
                this.ayarlar.bittiMi = true;
                toplamScore += score;
                this.ayarlar.score = toplamScore;
                paused = true;
                //Gdx.app.exit();
                actionResolver.showAlertBox("Oyun Bitti!", "Puan: " + Integer.toString(toplamScore), "Ana Menü", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        return exit();
                    }
                });

            }
        }

        if (!paused) {
            Gdx.graphics.requestRendering();
        }

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() { //oyun durduðunda veya kapatildiðinda oyununda boþ yere kaplamsin diye verileri siler


    }

    public static int randInt(int min, int max) {  //kaynak : http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Void exit(){
        Gdx.app.exit();
        return null;
    }
}
