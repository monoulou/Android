package fr.am.androidsqlitecontentresolver;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;

/*
 * ContentResolverSQLite : un ContentResolver
 * Utilisation des donnees du ContentProvider suivant : fr.am.sql.UtilitairesJava.FournisseurSQLite
 */
public class ContentResolverSQLite extends Activity {

    private ListView listViewVilles;
    private TextView textViewMessage;
    private List<String> listeVilles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resolver_sqlite);

        listViewVilles = (ListView) findViewById(R.id.listViewVilles);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        StringBuilder lsbContenu = new StringBuilder();
        listeVilles = new ArrayList<String>();

        try {
			/*
			 * Exemple avec le ContentResolver SQL
			 */
            ContentResolver cr = getContentResolver();

			/*
			 * Dans le ContentProvider
			 * public static final Uri CONTENT_URI = Uri.parse("content://fr.pb.sql.FournisseurSQLite");
			 */

            String lsURI = "content://fr.am.sqlite.FournisseurSQLite";

            Uri uri = Uri.parse(lsURI);

            String[] tCols = {"cp", "nom_ville"};

            // --- Utilisation de la methode query()
            // --- query(uri, tCols, restriction, tValeurs, ORDER BY);
            // --- selectAll tries sur le CP
            Cursor curseur = cr.query(uri, tCols, null, null, "cp");

            while (curseur.moveToNext()) {
                listeVilles.add(curseur.getString(0) + " - " + curseur.getString(1));
            }
            curseur.close();

            // --- Utilisation de la methode delete()
            String lsWhere = "cp=?";
            String[] tArgsWhere = {"75021"};
            int liSupprime = cr.delete(uri, lsWhere, tArgsWhere);
            lsbContenu.append("\nSupprimé(s) : " + Integer.toString(liSupprime));

            ArrayAdapter<String> aaListe = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeVilles);

            listViewVilles.setAdapter(aaListe);
        } catch (Exception e) {
            lsbContenu.append("Erreur : " + e.getMessage());
        }

        textViewMessage.setText(lsbContenu.toString());

    } // / onCreate

} // / classe ContentResolverSQLite
