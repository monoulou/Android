package fr.am.webandroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/*
 * HtmlJS :
 * bug mortel : https://code.google.com/p/android/issues/detail?id=12987
 */
public class HtmlJS extends Activity {

    private WebView navigateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigateur_web);

        navigateur = (WebView) findViewById(R.id.navigateur);

        // Permet d'avoir l'Alert JS
        navigateur.setWebChromeClient(new WebChromeClient());

        // Permet l'execution de code JS
        navigateur.getSettings().setJavaScriptEnabled(true);

        // Chargement de la page d'accueil
        // assets = ressource basique
        // file:///android_asset/ == /assets/ ! attention au S !!!
        navigateur.loadUrl("file:///android_asset/www/accueil.html");
    } // / onCreate
} // / HtmlJS
