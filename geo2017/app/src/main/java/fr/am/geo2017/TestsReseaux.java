package fr.am.geo2017;

import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import java.util.List;

/**
 *
 * @author pascal
 *
 * Tests pour savoir si les Services de geolocalisation sont disponibles
 *
 */
public class TestsReseaux extends Activity implements OnClickListener {

    // Attribut pour les widgets
    private Button buttonTestWIFI;
    private Button buttonTestGPS;
    private Button buttonTestPassive;
    private TextView textViewMessageTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tests_reseaux);
        initInterface();

    } // / onCreate

    private void initInterface() {

        // Liaison widget <--> Attribut
        buttonTestWIFI = (Button) findViewById(R.id.buttonTestWIFI);
        buttonTestGPS = (Button) findViewById(R.id.buttonTestGPS);
        buttonTestPassive = (Button) findViewById(R.id.buttonTestPassive);
        textViewMessageTests = (TextView) findViewById(R.id.textViewMessageTests);

        buttonTestWIFI.setOnClickListener(this);
        buttonTestGPS.setOnClickListener(this);
        buttonTestPassive.setOnClickListener(this);

    } // / initInterface

    @Override
    public void onClick(View vue) {

        StringBuilder lsbMessage = new StringBuilder();

        // Le manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Liste des capteurs presents sur le terminal
        List<String> listeFournisseurs = locationManager.getAllProviders();

        if (vue == buttonTestWIFI) {
            // Test pour savoir si le capteur est present sur le terminal
            if(listeFournisseurs.contains("network")) {
                // Test pour savoir si le service de geolocalisation via le WIFI est actif
                // donc
                lsbMessage.append("Capteur WIFI présent sur le terminal\n");
                boolean lbWifiActif = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if (lbWifiActif) {
                    lsbMessage.append("Wi-Fi activé\n");
                    // Test pour savoir si le Android Manifest est bien configure
                    // Test pour savoir si le WIFI est disponible
                    WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//                    int etatWifi = wifiManager.getWifiState();
//                    if(etatWifi== WifiManager.WIFI_STATE_ENABLED){
                    if(wifiManager.isWifiEnabled()){
                        lsbMessage.append("Wi-Fi disponible\n");

                    }
                    else {
                        lsbMessage.append("Wi-Fi indisponible\n");
                    }
//                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                    supState = wifiInfo.getSupplicantState();

                } else {
                    lsbMessage.append("Wi-Fi désactivé\n");
                }
            }
            else {

            }

        } /// WIFI

        if (vue == buttonTestGPS) {
            // Test pour savoir si le capteur est present sur le terminal
            if(listeFournisseurs.contains("gps")) {
                lsbMessage.append("GPS présent sur le terminal\n");
                // Test pour savoir si le service de geolocalisation via le GPS est disponible
                boolean lbGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (lbGPS) {
                    lsbMessage.append("GPS actif sur le terminal\n");

                } else {
                    lsbMessage.append("GPS désactivé sur le terminal\n");
                }
            }
            else {
                lsbMessage.append("GPS absent sur le terminal\n");
            }

        } /// GPS

        if (vue == buttonTestPassive) {
            // Test pour savoir si le capteur est present sur le terminal

            // Test pour savoir si le service de geolocalisation via le "passive" est disponible
            if(listeFournisseurs.contains("passive")) {
                lsbMessage.append("Passive présent sur le terminal\n");
                boolean lbPassive = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
                if (lbPassive) {
                    lsbMessage.append("Passive actif sur le terminal\n");
                } else {
                    lsbMessage.append("Passive désactivé sur le terminal\n");
                }
            }

        } /// Passive

        textViewMessageTests.setText(lsbMessage.toString());

    } // / onClick

} // / class
