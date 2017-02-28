package fr.am.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

import fr.am.sqlite.GestionnaireOpenSQLite;


/*
 * Classe qui sert de fournisseur de donnees SQL (SQLite en l'occurrence)
 *
 * Methodes : onCreate, query, insert, update, delete, getType
 */
public class FournisseurSQLite extends ContentProvider {

    private SQLiteDatabase db;
    private static final String TABLE_NAME = "villes";



    public static final Uri CONTENT_URI = Uri.parse("content://fr.am.sqlite.FournisseurSQLite");

    @Override
    public boolean onCreate() {
        Context context = getContext();
        GestionnaireOpenSQLite dbHelper = new GestionnaireOpenSQLite(context, null);
        this.db = dbHelper.getWritableDatabase();
        return (this.db == null) ? false : true;
    } /// onCreate



    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor curseur = null;
        // --- Tous les enregistrements
        try {
            // --- query(table, tColonnes, sWhere, tParamsWhere, sGroupBy, sHaving, sOrdeBy)
            curseur = this.db.query("villes", projection, selection, selectionArgs, null, null, sortOrder);
        }
        catch(SQLiteException e) {
            curseur = null;
        }
        return curseur;
    } /// query



    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /*
        * Renvoi une nouvelle URI contenant le num de la ligne à inserer
        */
        Uri u = null;
        try {
            //long insert (String table, String nullColumnHack, ContentValues values)
            //long rowID = this.ibd.insert(uri.getLastPathSegment(), null, values);
            long rowID = this.db.insert("villes", null, values);
            //u = ContentUris.withAppendedId(getContentUri(), rowID);
            // Renvoie le nouvel ID si autoincrement ou autre type _ID
            u = Uri.parse(String.valueOf(rowID));
        } catch (SQLiteException e) {
            u = null;
        }
        return u;
    } /// insert

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        /*
	    * UPDATE ...
	    */
        int liAffecte = 0;

        try {
            // liAffecte = this.ibd.update(uri.getLastPathSegment(), values, selection, selectionArgs);
            liAffecte = this.db.update("villes", values, selection, selectionArgs);
        } catch (SQLiteException e) {
            liAffecte = -1;
        }

        return liAffecte;
    } /// update



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        // --- delete(table, sWhere, tWhere)
        int count = this.db.delete("villes", "cp=?", selectionArgs);
        return count;
    } /// delete



    @Override
    public String getType(Uri uri) {
        return null;
    } /// getType
}///Class
