package fr.am.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.Cursor;
import android.content.ContentValues;

/*
 * DAO
 * Methodes : constructeur, insert, delete, selectOne, selectAll
 */
public class VilleDAO {

    private static final String TABLE_NAME = "villes";
    private SQLiteDatabase ibd;

    // --- CONSTRUCTEUR
    public VilleDAO(SQLiteDatabase bd) {
        this.ibd = bd;
    } // / VilleDAO()



    /**
     *
     * @param ville
     * @return
     */
    public boolean insert(Ville ville) {

        boolean lbOK = false;

        // --- Un enregistrement sous forme de HashMap
        ContentValues hmColonnesValeurs = new ContentValues();
        hmColonnesValeurs.put("cp", ville.getCp());
        hmColonnesValeurs.put("nom_ville", ville.getNomVille());
        hmColonnesValeurs.put("id_pays", ville.getIdPays());

        // --- Insertion
        try {
            // public long insert (String table, String nullColumnHack, ContentValues values)
            this.ibd.insert(TABLE_NAME, null, hmColonnesValeurs);
            lbOK = true;
        } catch (SQLiteException e) {
        }

        return lbOK;

    } // / insert()

    /**
     *
     * @param asCP
     * @return
     */
    @Deprecated
    public boolean delete(String asCP) {

        boolean lbOK = false;
        String[] tValeurs = new String[1];
        tValeurs[0] = asCP;

        try {
            // public int delete (String table, String whereClause, String[] whereArgs)
            this.ibd.delete(TABLE_NAME, "cp=?", tValeurs);
            lbOK = true;
        } catch (SQLiteException e) {
        }

        return lbOK;

    } // / delete()

    /**
     * selectAll : renvoie tous les enregistrements
     *
     * @return une String
     * (devrait renvoyer une List d'objets,
     *  une List de Ville en l'occurrence)
     */
    @Deprecated
    public String selectAll() {

        StringBuilder lsbResultat = new StringBuilder();

        // --- Tous les enregistrements
        try {
            String[] cols = { "cp", "nom_ville" , "id_pays"};

            Cursor curseur = this.ibd.query(TABLE_NAME, cols, null, null, null, null, null);

            // Cursor curseur =
            // this.ibd.rawQuery("SELECT cp, nom_ville FROM villes", null);
            while (curseur.moveToNext()) {
                lsbResultat.append(curseur.getString(0));
                lsbResultat.append(" - ");
                lsbResultat.append(curseur.getString(1));
                lsbResultat.append(" - ");
                lsbResultat.append(curseur.getString(2));
                lsbResultat.append("\n");
            }
        } catch (SQLiteException e) {
            lsbResultat.append("Erreur de lecture ");
        }

        return lsbResultat.toString();
    } // / selectAll()



    /**
     * selectOne : un enregistrement
     *
     * @param asCP
     * @return : une ville
     */
    public Ville selectOne(String asCP) {

        Ville ville = new Ville();

        // --- Un enregistrement
        try {
            String[] cols = { "cp", "nom_ville" , "id_pays"};
            String[] tValeurs = { asCP };

            // query(String table, String[] columns, String selection, String[]
            // selectionArgs, String groupBy, String having, String orderBy)
            Cursor curseur = this.ibd.query(TABLE_NAME, cols, "cp=?", tValeurs, null, null, null);

            if (curseur.moveToNext()) {
                ville.setCp(asCP);
                ville.setNomVille(curseur.getString(1));
                ville.setIdPays(curseur.getString(2));
            } else {
                ville.setCp("");
                ville.setNomVille("");
                ville.setIdPays("");
            }
        } catch (SQLiteException e) {
            ville.setCp("Erreur de lecture ");
            ville.setNomVille(e.getMessage());
            ville.setIdPays("");
        }

        return ville;

    } // / selectOne()

}///Class
