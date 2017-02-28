package fr.am.webandroid;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import fr.am.webandroid.R;

// --------------------------------------
public class Navigateur1 extends Activity {

    private WebView navigateur;

    @Override
    // -----------------
    public void onCreate(Bundle savedInstanceState) {
        // -----------------
        super.onCreate(savedInstanceState);

        // --- Le layout
        setContentView(R.layout.navigateur1);

        // --- Le widget WebView
        navigateur = (WebView) findViewById(R.id.navigateur);

        // --- Charge une page dans le navigateur 'perso' ie la WebView du layout
        //navigateur.loadUrl("http://www.meteo-paris.com/ile-de-france/previsions.php");
        navigateur.loadUrl("http://172.26.10.87/accueil.html");


    } /// onCreate()

} /// classe