package fr.am.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DynamicMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager gestionnaireGeoloc;
    private LocationListener ecouteurGeoloc;
    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_maps);




        try {
            gestionnaireGeoloc = (LocationManager) getSystemService(this.LOCATION_SERVICE);

            boolean lbGPS = gestionnaireGeoloc
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (lbGPS) {
                Toast.makeText(this, "GPS disponible", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "GPS indisponible", Toast.LENGTH_LONG).show();
                //textViewMessage.setText("GPS indisponible");
            }

            ecouteurGeoloc = new EcouteurPosition();

                // public void requestLocationUpdates (String provider, long
                // minTime, float minDistance, LocationListener listener)
                // 1000 * 60 = 1 minute
                // 10 metres
                gestionnaireGeoloc.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 1000 * 60, 5, ecouteurGeoloc);
        }catch(SecurityException e){
            Log.e("Security Exception", e.getMessage());
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }///onCreate

    protected void onPause() {
        super.onPause();
        try {
            gestionnaireGeoloc.removeUpdates(ecouteurGeoloc);
        } catch (SecurityException e) {
            Toast.makeText(this, "SecurityException", Toast.LENGTH_SHORT).show();
        }
    } /// onPause


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in ??? !!! and move the camera
        LatLng ville = new LatLng(lat, lng);

        mMap.addMarker(new MarkerOptions().position(ville).title("Marker in Ville"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ville));

    }///onMapReady


    /*
    * Ajoute un marker et deplace la camera
    */
    public void changeMarker(){

        // Add a marker in ??? and move the camera
        LatLng ville = new LatLng(lat, lng);

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(ville).title("Marker in ville"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ville));

    }///changeMarker


    private class EcouteurPosition implements LocationListener {

        public void onProviderDisabled(String provider) {
//            textViewMessage.setText("onProviderDisabled");
            //locationManager.removeUpdates(this);
        }

        public void onProviderEnabled(String provider) {
//            textViewMessage.setText("onProviderEnabled");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
//            textViewMessage.setText("onStatusChanged");
        }

        public void onLocationChanged(Location location) {





            Toast.makeText(getBaseContext(), "Latitude : " + Double.toString(location.getLatitude()), Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), "Longitude : " + Double.toString(location.getLongitude()), Toast.LENGTH_LONG).show();

            Log.e("onLocationChanged", "Latitude : " + Double.toString(location.getLatitude()));
            Log.e("onLocationChanged", "Longitude : " + Double.toString(location.getLongitude()));

            lat = location.getLatitude();
            lng = location.getLongitude();

            changeMarker();
        }

    } // / private nested class

}///Class DynamicMap
