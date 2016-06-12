package com.gdxballoon.game;

import java.util.concurrent.Callable;

/**
 * Created by selman on 18.05.2016.
 */
public interface ActionResolver {
    public static final int VIEW_TEST = 1;

    public void showShortToast(CharSequence toastMessage);
    public void showLongToast(CharSequence toastMessage);
    public void showAlertBox(String alertBoxTitle, String alertBoxMessage, String alertBoxButtonText, Callable<Void> func);
    public void openUri(String uri);
    /*
    public void showView(final int view);
    public void hideView(final int view);
    */
}