package fr.am.geo2017;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.*;
import android.view.View;
import android.view.View.*;

// -----------------------------
public class GeolocalisationGPS extends Activity implements OnClickListener {

    // Attributs pour les widgets
    private TextView textViewLongitude;
    private TextView textViewLatitude;
    //private Button buttonActiverWIFI;
    //private Button buttonDesactiverWIFI;
    private Button buttonDemarrerGeolocalisation;
    private Button buttonArreterGeolocalisation;
    private TextView textViewMessage;

    private boolean ibEtatScanne = false;

    private LocationManager gestionnaireGeoloc;
    private LocationListener ecouteurGeoloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.geolocalisation_gps);
        initInterface();
        initEvents();

        gestionnaireGeoloc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getEtatGPS();
    } // / onCreate

    @Override
    protected void onResume() {
        super.onResume();
    } /// onResume

    @Override
    protected void onPause() {
        super.onPause();
        //gestionnaireGeoloc.removeUpdates(ecouteurGeoloc);
    } /// onPause


    private void getEtatGPS() {
        boolean lbGPS = gestionnaireGeoloc
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (lbGPS) {
            Toast.makeText(this, "GPS disponible", Toast.LENGTH_LONG).show();
            textViewMessage.setText("GPS disponible");
        } else {
            Toast.makeText(this, "GPS indisponible", Toast.LENGTH_LONG).show();
            textViewMessage.setText("GPS indisponible");
        }
    }

    private void initInterface() {
        // Liaison widget <--> Attribut
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
        textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
        buttonDemarrerGeolocalisation = (Button) findViewById(R.id.buttonDemarrerGeolocalisation);
        buttonArreterGeolocalisation = (Button) findViewById(R.id.buttonArreterGeolocalisation);
        //buttonActiverWIFI = (Button) findViewById(R.id.buttonActiverWIFI);
        //buttonDesactiverWIFI = (Button) findViewById(R.id.buttonDesactiverWIFI);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    } // / initInterface

    private void initEvents() {
        // Liaison widget <--> Events
        buttonDemarrerGeolocalisation.setOnClickListener(this);
        buttonArreterGeolocalisation.setOnClickListener(this);
        //buttonActiverWIFI.setOnClickListener(this);
        //buttonDesactiverWIFI.setOnClickListener(this);
    } // / initEvents

    @Override
    public void onClick(View v) {

        try {
            if (v == buttonDemarrerGeolocalisation) {
                textViewMessage.setText("Etat : démarré");

                ecouteurGeoloc = new EcouteurPosition();
                // public void requestLocationUpdates (String provider, long
                // minTime, float minDistance, LocationListener listener)
                // 1000 * 60 = 1 minute
                // 10 metres
                gestionnaireGeoloc.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 1000 * 60, 10, ecouteurGeoloc);
                ibEtatScanne = true;
            }

            if (v == buttonArreterGeolocalisation) {
                if (ibEtatScanne) {
                    gestionnaireGeoloc.removeUpdates(ecouteurGeoloc);
                    textViewMessage.setText("Etat : arrêté");
                    ibEtatScanne = false;
                } else {
                    textViewMessage
                            .setText("Le service de géolocalisation via le GPS n'était pas démarré");
                }
            }
/*
            if (v == buttonActiverWIFI) {
                //startWifi();
            }

            if (v == buttonDesactiverWIFI) {
                stopWifi();

            }///try
*/
        }catch(SecurityException e){
            Log.e("Security Exception", e.getMessage());
        }///catch

    } // / onClick


    /**
     *
     */

    //public void startWifi() {
        /*
         *  avec la permission
		 *  <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
		 */
       // Toast.makeText(this, "Le WiFi est maintenant activé !",
                //Toast.LENGTH_SHORT).show();
        //WifiManager wM = (WifiManager) getSystemService(WIFI_SERVICE);
        //wM.setWifiEnabled(true);
    //} // / startWifi

    /**
     *
     */
   // public void stopWifi() {
        //Toast.makeText(this, "Le WiFi est maintenant désactivé !",
                //Toast.LENGTH_SHORT).show();
        //WifiManager wM = (WifiManager) getSystemService(WIFI_SERVICE);
        //wM.setWifiEnabled(false);
    //} // / stopWifi

    /**
     * NESTED CLASS pour le Location Listener
     */
    private class EcouteurPosition implements LocationListener {

        public void onProviderDisabled(String provider) {
            textViewMessage.setText("onProviderDisabled");
            // locationManager.removeUpdates(this);
        }

        public void onProviderEnabled(String provider) {
            textViewMessage.setText("onProviderEnabled");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            textViewMessage.setText("onStatusChanged");
        }

        public void onLocationChanged(Location location) {
            textViewMessage.setText("onLocationChanged");
            // Affichage des valeurs lat et long
            textViewLongitude.setText(Double.toString(location.getLatitude()));
            textViewLatitude.setText(Double.toString(location.getLongitude()));
        }

    } // / private nested class

} // / class

