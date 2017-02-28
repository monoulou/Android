package fr.am.webandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVFromWeb extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewCSV;
    private Button buttonLoad;
    private ProgressBar barreDeProgression;
    private TextView textViewChargement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv_form_web);
        //new TacheAsynchrone().execute();
        initInterface();
        initEvents();
    }///onCreate

    // -----------------------
    private void initInterface() {

        textViewCSV = (TextView) findViewById(R.id.textViewCSV);
        buttonLoad = (Button) findViewById(R.id.buttonLoad);
        barreDeProgression = (ProgressBar) findViewById(R.id.barreDeProgression);
        textViewChargement = (TextView) findViewById(R.id.textViewChargement);
    }///initInterface

   //-----------------------
    private void initEvents() {
        // Liaison widget <--> Events
        buttonLoad.setOnClickListener(this);
    } /// initEvents

    @Override
    public void onClick(View v) {
        textViewCSV.setText("");
        textViewChargement.setText("");

        //InputStream is = this.getResources().openRawResource(R.raw.communes);
        new TacheAsynchrone().execute();
    } /// onClick

    private class TacheAsynchrone extends AsyncTask<String, Integer, List<String>> {

        @Override
        // ----------------------------------
        protected List<String> doInBackground(String... asParametres) {
            String lsURL;
            List<String> liste = new ArrayList<>();
            lsURL = "http://pascalbuguet.alwaysdata.net/PourSmartphones/";
            String lsRessource = "pays.txt";

            URL urlConnection = null;
            HttpURLConnection httpConnection = null;

            // Se deplace dans un thread d'arriere-plan
            int liProgressionPourcentage = 0;
            int liCompteurEnrs = 0;
            //InputStream is = isParametres[0];
            StringBuilder lsbContenu = new StringBuilder();

            try{
                // Instanciation de HttpURLConnection avec l'objet url
                urlConnection = new URL(lsURL + lsRessource);
                httpConnection = (HttpURLConnection) urlConnection.openConnection();

                // Connexion
                httpConnection.connect();
                // EXECUTION DE LA REQUETE ET RESPONSE
                InputStream is = httpConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String lsLigne = "";
                while ((lsLigne = br.readLine()) != null) {
                    liste.add(lsLigne);

                    // 38 951 enregistrements
                    if (liCompteurEnrs % 390 == 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        publishProgress(liProgressionPourcentage);
                        liProgressionPourcentage++;
                    }
                }
                br.close();
                is.close();

            }catch(IOException e){
                liste.add(e.getMessage());
            }finally {
                // Deconnexion
                httpConnection.disconnect();
            }
            return liste;
        }///doInBackground

        @Override
        // ----------------------------
        protected void onProgressUpdate(Integer... aiProgressions) {
            // Synchronisation avec le thread de l'UI
            // MAJ de la barre de progression
            barreDeProgression.setProgress(aiProgressions[0]);
            textViewChargement.setText(Integer.toString(aiProgressions[0]) + " %");
            //editTextContenu.setText();
        } // / onProgressUpdate

        protected void onPostExecute(List<String> liste) {
            // Synchronisation avec le thread de l'UI
            // Affiche le resultat final
            StringBuilder lsb = new StringBuilder();
            for (String element : liste) {
                lsb.append(element);
                lsb.append("\n");
            }
            textViewCSV.setText(lsb.toString());
            //barreDeProgression.setProgress(Integer.valueOf(asResultat));
            barreDeProgression.setProgress(100);
            textViewChargement.setText("100 %");
        } /// onPostExecute
    }///TacheAsynchrone
}///class
