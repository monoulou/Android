package fr.am.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class SQLiteExemple extends AppCompatActivity implements View.OnClickListener {

    /*
	 * Attributs
	 */
    private GestionnaireOpenSQLite gos;
    private SQLiteDatabase ibd;
    private VilleDAO idao;
    private Context contexte;

    private boolean ibOK;

    private ImageButton buttonSeConnecter;
    private ImageButton buttonSeDeconnecter;
    private ImageButton buttonAjouter;
    private ImageButton buttonSupprimer;
    private ImageButton buttonVoir;

    private EditText editTextCp;
    private EditText editTextNomVille;
    private EditText editTextIdPays;

    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_exemple);

        // ------------------------------------------
        // --- Initialisation de l'interface statique
        // ------------------------------------------
        initInterface();

        /*
		 * EN TEST
		 *editTextCp.setText("75021");
         *editTextNomVille.setText("Paris 21");
         *editTextIdPays.setText("33");
        */
        // ---------------------------------------------
        // --- Initialisation des variables de programme
        // ---------------------------------------------
        this.contexte = getBaseContext();
        this.ibd = null;
    }///onCreate

    @Override
    public void onClick(View v) {
        String lsMessage = "";

        if (v == buttonSeConnecter) {
            try {
                // --- GestionnaireOuvertureSQLite(Contexte, Fabrique de curseur);
                this.gos = new GestionnaireOpenSQLite(this.contexte, null);
                this.ibd = gos.getWritableDatabase();
                this.idao = new VilleDAO(this.ibd);

                lsMessage = "Connexion réussie";
            } catch (Exception e) {
                lsMessage = "Connexion ratée : " + e.getMessage();
            }
            textViewMessage.setText(lsMessage);
        } // / if buttonSeConnecter

        if (v == buttonSeDeconnecter) {
            try {
                this.gos.close();
                this.ibOK = false;
                this.ibd = null;
                lsMessage = "Vous êtes déconnecté(e) !";
            } catch (Exception e) {
                lsMessage = "Erreur Déconnexion : " + e.getMessage();
            }
            textViewMessage.setText(lsMessage);
        } // / if buttonSeDeconnecter

        if (v == buttonAjouter) {
            try {
                if (this.ibd != null) {
                    Ville ville = new Ville(this.editTextCp.getText().toString(), this.editTextNomVille.getText().toString(),
                            this.editTextIdPays.getText().toString());
                    ibOK = idao.insert(ville);
                    if (ibOK) {
                        lsMessage = "Insertion OK";
                    } else {
                        lsMessage = "Insertion KO";
                    }
                } else {
                    lsMessage = "Vous devez être connecté(e) !";
                }
            } catch (Exception e) {
                lsMessage = "Erreur Déconnexion : " + e.getMessage();
            }
            textViewMessage.setText(lsMessage);
        } // / if buttonAjouter

        if (v == buttonSupprimer) {
            try {
                if (this.ibd != null) {
                    ibOK = idao.delete(editTextCp.getText().toString());
                    if (ibOK) {
                        lsMessage = "Suppression OK !";
                    } else {
                        lsMessage = "Suppression KO !";
                    }
                } else {
                    lsMessage = "Vous devez être connecté(e) !";
                }
            } catch (Exception e) {
                lsMessage = "Erreur Delete : " + e.getMessage();
            }
            textViewMessage.setText(lsMessage);
        } // / if buttonSupprimer

        if (v == buttonVoir) {
            Ville ville = null;

            try {
                if (this.ibd != null) {
                    // selectAll
                    if (editTextCp.getText().toString().equals("")) {
                        lsMessage = idao.selectAll();
                        if (lsMessage.equals("")) {
                            lsMessage = "Aucun enregistrement";
                        }
                    }
                    // selectOne
                    else {
                        ville = idao.selectOne(editTextCp.getText().toString());
                        if (ville.getNomVille().equals("")) {
                            lsMessage = "Aucun enregistrement";
                        } else {
                            lsMessage = ville.toString();
                        }
                    }
                } else {
                    lsMessage = "Vous devez être connecté(e) !";
                }
            } catch (Exception e) {
                lsMessage = e.getMessage();
            }
            textViewMessage.setText(lsMessage);
        } // / if buttonVoir

    }///onClick

    // -----------------------
    private void initInterface() {
        // --- Liaison "variables" et composants du layout
        buttonSeConnecter = (ImageButton) findViewById(R.id.buttonSeConnecter);
        buttonSeDeconnecter = (ImageButton) findViewById(R.id.buttonSeDeconnecter);
        buttonAjouter = (ImageButton) findViewById(R.id.buttonAjouter);
        buttonSupprimer = (ImageButton) findViewById(R.id.buttonSupprimer);
        buttonVoir = (ImageButton) findViewById(R.id.buttonVoir);

        editTextCp = (EditText) findViewById(R.id.editTextCp);
        editTextNomVille = (EditText) findViewById(R.id.editTextNomVille);
        editTextIdPays = (EditText) findViewById(R.id.editTextIdPays);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

		/*
		 * Les procedures evenementielles
		 */

        // ----------------------------- SE CONNECTER
        buttonSeConnecter.setOnClickListener(this);
        // ----------------------------- SE DECONNECTER
        buttonSeDeconnecter.setOnClickListener(this);
        // ----------------------------- AJOUTER
        buttonAjouter.setOnClickListener(this);
        // ----------------------------- SUPPRIMER
        buttonSupprimer.setOnClickListener(this);
        // ----------------------------- VOIR VILLES
        buttonVoir.setOnClickListener(this);

    } // / initInterface

}///Class
