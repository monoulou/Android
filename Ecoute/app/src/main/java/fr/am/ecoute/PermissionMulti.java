package fr.am.ecoute;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PermissionMulti extends AppCompatActivity implements View.OnClickListener {

    private final int PERMISSION_REQUEST_USE_CAMERA = 1;
    private final int PERMISSION_REQUEST_USE_RECORD_AUDIO = 2;
    private final int PERMISSION_REQUEST_USE_READ_STORAGE = 3;
    private final int PERMISSION_REQUEST_USE_WRITE_STORAGE = 4;

    private final int PERMISSION_REQUEST_ALL = 99;

    private CheckBox checkBoxCamera;
    private CheckBox checkBoxRecord;
    private CheckBox checkBoxStorage;

    private Button buttonValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_multi);

        checkBoxCamera = (CheckBox) findViewById(R.id.checkBoxCamera);
        checkBoxRecord = (CheckBox) findViewById(R.id.checkBoxRecord);
        checkBoxStorage = (CheckBox) findViewById(R.id.checkBoxStorage);

        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(this);


    }///onCreate

    private void permissions() {
        int permissionCheckCamera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        int permissionCheckRecord = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        int permissionCheckStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);


    }///permissions

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_USE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission d'utiliser la Camera accordée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission d'utiliser la Camera refusée", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case PERMISSION_REQUEST_USE_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission d'utiliser le micro accordée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission d'utiliser le micro refusée", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case PERMISSION_REQUEST_USE_READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission de lire le storage accordée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission de lire le storage refusée", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case PERMISSION_REQUEST_USE_WRITE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission d'écrire le storage accordée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission d'écrire le storage refusée", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case PERMISSION_REQUEST_ALL: {
                //Log.i("PERMISSION_REQUEST_ALL", Integer.toString(grantResults.length));
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {

                    for (int i = 0; i < permissions.length; i++) {
                        Toast.makeText(this, permissions[i], Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "La requête a été annulée", Toast.LENGTH_SHORT).show();
                }
                return;
                }
            } /// switch
    }///onRequestPermissionsResult

    @Override
    public void onClick(View v) {
        boolean lbEtatCamera = checkBoxCamera.isChecked();
        boolean lbEtatRecordAudio = checkBoxRecord.isChecked();
        boolean lbEtatStorage = checkBoxStorage.isChecked();

        List<String> listePermissions = new ArrayList();
        if (lbEtatCamera) {
            listePermissions.add(Manifest.permission.CAMERA);
        }
        if (lbEtatRecordAudio) {
            listePermissions.add(Manifest.permission.RECORD_AUDIO);
        }
        if (lbEtatStorage) {
            listePermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (listePermissions.size() > 0) {
            String[] tPermissions = listePermissions.toArray(new String[listePermissions.size()]);

            ActivityCompat.requestPermissions(this, tPermissions, PERMISSION_REQUEST_ALL);
        }

    }///onClick
}///Class
