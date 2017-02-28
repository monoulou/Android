package fr.am.nonsql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

public class JsonCRUD extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextISO2;
    private EditText editTextNomPays;
    private Button buttonAjouter;
    private Button buttonSupprimer;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_crud);

        initInterface();
        initEvent();
    }///onCreate

    // -----------------------
    private void initInterface() {
        editTextISO2 = (EditText) findViewById(R.id.editTextISO2);
        editTextNomPays = (EditText) findViewById(R.id.editTextNomPays);

        textViewMessage  = (TextView) findViewById(R.id.textViewMessage);

        buttonAjouter = (Button) findViewById(R.id.buttonAjouter);
        buttonSupprimer = (Button) findViewById(R.id.buttonSupprimer);


    }/// initInterface

    private void initEvent(){

        buttonAjouter.setOnClickListener(this);
        buttonSupprimer.setOnClickListener(this);

    }///initEvent

    @Override
    public void onClick(View v) {

        if(v == buttonAjouter){
            JSONObject objet = new JSONObject();
            try {
                objet.put("iso2", editTextISO2.getText().toString());
                objet.put("nom", editTextNomPays.getText().toString());

                //Log.e("log", objet.get("iso2").toString());
                //Log.e("log", objet.get("nom").toString());

                JSONUtilitaires.insert(this, objet, "pays.json");

                textViewMessage.setText("Ajout avec succès de iso2:"+ editTextISO2.getText() + "nom :" + editTextNomPays.getText());

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(v == buttonSupprimer){
            try {

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }///onClick
}/// Class
