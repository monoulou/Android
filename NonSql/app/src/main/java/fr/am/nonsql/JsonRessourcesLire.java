package fr.am.nonsql;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import java.io.InputStream;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.am.nonsql.JSONUtilitaires;

/*
 * Lit des donnees JSON stockees dans /res/raw/villes.json
 * avec la bibliotheque JSONSimple
 */
public class JsonRessourcesLire extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private Spinner spinnerResultats;
    private TextView textViewSelection;
    private JSONArray tableauJSON;
    //private List<Map<String, String>> listeVilles;
    //private JSONArray villes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_ressources_lire);

        initInterface();

        try {
            // Récupération d'un flux
            InputStream is = null;
            is = getBaseContext().getResources().openRawResource(R.raw.villes);
            // Methode perso pour recuperer du JSON
            tableauJSON = JSONUtilitaires.jsonIS2JsonArray(is);
            List<String> listeNoms = new ArrayList<>();
            for (int i = 0; i < tableauJSON.length(); i++) {
                JSONObject jsonObject = (JSONObject) tableauJSON.get(i);
                listeNoms.add(jsonObject.get("Capitale").toString());
            }

            // --- Le spinner avec les resultats
            ArrayAdapter<String> aaResultats = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeNoms);
            aaResultats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // --- Affectation de l'ArrayAdapter à la liste du spinner
            spinnerResultats.setAdapter(aaResultats);
        } catch (Exception e) {
            textViewSelection.setText(e.getMessage());
        }
    }///onCreate

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            JSONObject objetJSON = (JSONObject) tableauJSON.get(position);
            textViewSelection.setText(objetJSON.get("cp").toString());
        } catch (Exception e) {
            textViewSelection.setText(e.getMessage());
        }
    }///onItemSelected

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewSelection.setText("");
    }///onNothingSelected

    // -----------------------
    private void initInterface() {
        // --- On pointe vers le label
        textViewSelection = (TextView) findViewById(R.id.textViewSelection);
        // --- On pointe vers la liste deroulante
        spinnerResultats = (Spinner) findViewById(R.id.spinnerResultats);
        // --- Ajout d'un ecouteur a la liste deroulante
        spinnerResultats.setOnItemSelectedListener(this);

    } // / initInterface
}///class
