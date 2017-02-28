package fr.am.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Classe Helper pour la gestion de l'ouverture de la BD
 * La connexion a la BD en somme ou la creation et connexion si elle n'existe pas
 * Methodes : constructeur, onCreate, onUpgrade
 */
public class GestionnaireOuvertureSQLite extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "cours.db";

    private static final String DROP_TABLE_PAYS = "DROP TABLE IF EXISTS pays";
    private static final String CREATE_TABLE_PAYS = "CREATE TABLE IF NOT EXISTS pays(id_pays INTEGER PRIMARY KEY AUTOINCREMENT, nom_pays VARCHAR(50) NOT NULL UNIQUE)";

    // --- Constructeur
    // -------------------------------
    public GestionnaireOuvertureSQLite(Context contexte, SQLiteDatabase.CursorFactory fabrique) {
        // --- Cree la BD si elle n'existe pas
        // --- et de ce fait execute le code de onCreate()
        // --- Se connecte si elle existe
        super(contexte, DB_NAME, fabrique, DB_VERSION);
    }///GestionnaireOuvertureSQLite()

    @Override
    // -----------------
    public void onCreate(SQLiteDatabase abd) {
        // --- Creation de la table pays
        abd.execSQL(CREATE_TABLE_PAYS);

        /*
         * Pour les tests
         */
        // --- Remplissage de la table pays
        abd.execSQL("INSERT INTO pays(nom_pays) VALUES('France')");
    } /// onCreate()

    @Override
    // ------------------
    public void onUpgrade(SQLiteDatabase abd, int ancienneVersion, int nouvelleVersion) {
        // --- Suppression de la table puis appel a la creation des tables
        abd.execSQL(DROP_TABLE_PAYS);
        onCreate(abd);
    } /// onUpgrade()

}///Class
