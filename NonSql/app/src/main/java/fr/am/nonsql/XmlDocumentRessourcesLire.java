package fr.am.nonsql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * Lit des donnees XML stockees dans /res/xml/villes_doc.xml
 */
public class XmlDocumentRessourcesLire extends Activity implements OnItemSelectedListener {

    private Spinner spinnerResultats;
    private TextView textViewSelection;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_document_ressources);

        initInterface();

        List<String> listeVilles = lireRessourceXMLDoc(R.xml.villes_doc, "nom_ville");

        // --- Le spinner avec les resultats
        ArrayAdapter<String> aaResultats = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeVilles);
        aaResultats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // --- Affectation de l'ArrayAdapter à la liste du spinner
        spinnerResultats.setAdapter(aaResultats);

    } // / onCreate()

    // -----------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        textViewSelection.setText(parent.getItemAtPosition(position).toString());
    }///onItemSelected

    // --------------------------
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        textViewSelection.setText("");
    }

    /**
     *
     * @param aiRessource
     * @param asBalise
     * @return
     */
    private List<String> lireRessourceXMLDoc(int aiRessource, String asBalise) {
        // --- On cree une liste
        List<String> liste = new ArrayList<String>();

        try {
            // --- On ouvre le document XML
            XmlPullParser xpp = getResources().getXml(aiRessource);

            // --- Tant que le document n'a pas ete analyse jusqu'a la fin
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

                // --- Si c'est une balise ouvrante
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    // --- Si la balise s'appelle [XXX]
                    if (xpp.getName().equals(asBalise)) {
                        // --- Pour aller sur le noeud #text
                        liste.add(xpp.nextText());
                    } // / IF balise "XXX" trouvee

                } // / IF start_tag

                // --- On passe a la balise suivante ou element suivant
                xpp.next();

            } // / WHILE parse

        } // / try

        catch (Exception e) {
            textViewSelection.setText("Erreur" + e.getMessage());
        } // / catch

        return liste;

    } // / lireRessourceXML

    /**
     *
     */
    private void initInterface() {
        // --- On pointe vers le label
        textViewSelection = (TextView) findViewById(R.id.textViewSelection);

        // --- On pointe vers la liste deroulante
        spinnerResultats = (Spinner) findViewById(R.id.spinnerResultats);

        // --- Ajout d'un ecouteur a la liste deroulante
        //spinnerResultats.setOnItemSelectedListener(this);
        StringBuilder lsbContenu = new StringBuilder("");
        String lsLigne = "";
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;

        try {
            // --- Avec un buffer
            is = getBaseContext().getResources().openRawResource(R.raw.villes);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ((lsLigne = br.readLine()) != null) {
                lsbContenu.append(lsLigne);
                lsbContenu.append("\n");
            }
            br.close();
            isr.close();
            is.close();
        }
        catch(Exception e) {
            lsbContenu.append(e.getMessage());
        }
        textViewSelection.setText(lsbContenu.toString());
    }///initInterface
}///class