package fr.am.ecoute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionsCheck extends AppCompatActivity implements View.OnClickListener {

    private final int PERMISSION_REQUEST_USE_CAMERA = 2;
    private final int PERMISSION_REQUEST_USE_RECORD_AUDIO = 1;

    private Button buttonValider;
    private Button buttonFermer;
    private TextView textViewMessage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_check);


        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(this);
        buttonFermer = (Button) findViewById(R.id.buttonFermer);
        buttonFermer.setOnClickListener(this);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        permissions();
    }///onCreate

    /*
    PERMISSIONS
     */
    private void permissions() {

        /*
        Permet de connaître l'état d'une permission
         */
        /*
        CAMERA
         */
        int permissionCheckCamera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if (permissionCheckCamera == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Camera Granted", Toast.LENGTH_LONG).show();
        } else if (permissionCheckCamera == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Camera Denied", Toast.LENGTH_LONG).show();
        }
        /*
        INTERNET
         */
        int permissionCheckInternet = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);

        if (permissionCheckInternet == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Internet Granted", Toast.LENGTH_LONG).show();
        } else if (permissionCheckInternet == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Internet Denied", Toast.LENGTH_LONG).show();
        }
        /*
        RECORD_AUDIO
         */
        int permissionCheckRecordAudio = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        if(permissionCheckRecordAudio == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Record audio Granted", Toast.LENGTH_LONG).show();
        } else if (permissionCheckRecordAudio == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "Record audio Denied", Toast.LENGTH_LONG).show();
        }

        /*
        Permet de modifier l'état d'une permission
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_USE_CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_REQUEST_USE_RECORD_AUDIO);
        }

    } /// permissions

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_REQUEST_USE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission d'utiliser la Camera accordée", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission d'utiliser la Camera refusée", Toast.LENGTH_LONG).show();
                }
                return;
            }
            case PERMISSION_REQUEST_USE_RECORD_AUDIO: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission d'utiliser l'enregistrement vocal", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission d'utliser l'enregisttrement vocal refusé", Toast.LENGTH_LONG).show();
                }
                return;
            }

        } /// switch
    } /// onRequestPermissionsResult

    @Override
    public void onClick(View v) {
        if (v == buttonValider) {
            permissions();
        }
        if (v == buttonFermer) {
            finish();
        }

    }///onClick
}///Class
