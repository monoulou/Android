package fr.am.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

// --------------------------------------------
public class ListeDeroulante extends Activity implements OnItemSelectedListener {

    private EditText editTextPrenom;
    private EditText editTextNom;
    private Spinner spinnerFonctions;
    private Spinner spinnerServices;
    private TextView textViewSaisies;

    // --- Pour une liste deroulante a initialiser en code
    private String[] tServices = { "Finances", "Commercial", "Marketing", "Informatique", "Administratif" };
    private ArrayAdapter<String> aaServices;
    private String isFonction;
    private String isService;

    @Override
    // -----------------
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_deroulante);

        initInterface();

    } // / onCreate
    // -----------------------
    private void initInterface() {

		/*
		 * INITIALISATION DES COMPOSANTS
		 */
        editTextPrenom = (EditText) findViewById(R.id.editTextPrenom);
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        spinnerFonctions = (Spinner) findViewById(R.id.spinnerFonctions);
        spinnerServices = (Spinner) findViewById(R.id.spinnerServices);

        textViewSaisies = (TextView) findViewById(R.id.textViewSaisies);

		/*
		 * REMPLISSAGE DE LA LISTE DES SERVICES
		 */
        // --- Le spinner avec les services
        aaServices = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tServices);
        aaServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerServices.setAdapter(aaServices);

		/*
		 * GESTION EVENEMENTIELLE
		 */
        spinnerFonctions.setOnItemSelectedListener(this);
        spinnerServices.setOnItemSelectedListener(this);

        // INITIALISATION DES VARIABLES
        isFonction = "";
        isService = "";
    } // / initInterface

    @Override
    // --- Spinner, TextView, position, position
    public void onItemSelected(AdapterView<?> parent, View vue, int position, long id) {
        StringBuilder lsbSaisies = new StringBuilder();
        lsbSaisies.append(editTextPrenom.getText().toString());
        lsbSaisies.append(" ");
        lsbSaisies.append(editTextNom.getText().toString());
        lsbSaisies.append(" ");

		/*
		 * Le spinner spinnerFonctions est initialise a partir d'un tableau
		 * dans string.xml nomme fonctions
		 */
        if (parent == spinnerFonctions) {
            isFonction = parent.getItemAtPosition(position).toString();
        }
        lsbSaisies.append(" occupe la fonction de ");
        lsbSaisies.append(isFonction);

        if (parent == spinnerServices) {
            isService = parent.getSelectedItem().toString();
        }
        lsbSaisies.append(" dans le service ");
        lsbSaisies.append(isService);

        textViewSaisies.setText(lsbSaisies.toString());
    } // / onItemSelected

    @Override
    // --------------------------
    public void onNothingSelected(AdapterView<?> arg0) {
        textViewSaisies.setText("");
    } // / onNothingSelected

} // / class ListesDeroulantes
