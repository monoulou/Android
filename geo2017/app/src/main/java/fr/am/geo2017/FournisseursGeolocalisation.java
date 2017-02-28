package fr.am.geo2017;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
import java.util.List;
import android.location.LocationManager;

// -------------------------------------
public class FournisseursGeolocalisation extends Activity {

    // Attributs pour les widgets
    private TextView textViewFournisseurs;
    private TextView textViewFournisseursActifs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fournisseurs_geolocalisation);
        initInterface();

        afficherFournisseurs();
    } /// onCreate

    private void initInterface() {
        textViewFournisseurs = (TextView) findViewById(R.id.textViewFournisseurs);
        textViewFournisseursActifs = (TextView) findViewById(R.id.textViewFournisseursActifs);
    } /// initInterface

    private void afficherFournisseurs() {
        StringBuilder lsb = new StringBuilder();

        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Les capteurs presents sur le terminal
        List<String> fournisseurs = locationManager.getAllProviders();
        for (int i = 0; i < fournisseurs.size(); i++) {
            lsb.append(fournisseurs.get(i));
            lsb.append("\n");
        }
        textViewFournisseurs.setText(lsb.toString());

        lsb.setLength(0);
        // Les capteurs actifs (ON) sur le terminal
        List<String> fournisseursActifs = locationManager.getProviders(true);
        for (int i = 0; i < fournisseursActifs.size(); i++) {
            lsb.append(fournisseursActifs.get(i));
            lsb.append("\n");
        }
        textViewFournisseursActifs.setText(lsb.toString());
    } /// afficherFournisseurs

} /// class
