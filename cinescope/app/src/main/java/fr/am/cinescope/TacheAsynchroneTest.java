package fr.am.cinescope;

import android.content.Context;
import android.graphics.Path;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class TacheAsynchroneTest extends AppCompatActivity implements OnClickListener {


    private TextView textViewProgressionPourcentage;
    private Button buttonTacheAsynchrone;
    private ProgressBar barreDeProgression;
    private TextView textViewCSV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tache_asynchrone_test);



        initInterface();
        initEvents();

    }///onCreate

    // -----------------------
    private void initInterface() {

        textViewProgressionPourcentage = (TextView) findViewById(R.id.textViewProgressionPourcentage);
        buttonTacheAsynchrone = (Button) findViewById(R.id.buttonTacheAsynchrone);
        barreDeProgression = (ProgressBar) findViewById(R.id.barreDeProgression);

        //buttonTacheAsynchrone.setOnClickListener(this);
        buttonTacheAsynchrone.setText("TacheAsynchroneTest");

        textViewCSV = (TextView) findViewById(R.id.textViewCSV);
    } // / initInterface


    /*
    @Override
    public void onClick(View vue) {


        //InputStream is = this.getResources().openRawResource(R.raw.communes);
        // --- Tache asynchrone
        if (vue == buttonTacheAsynchrone) {
            new TacheAsynchrone().execute();
        } // / if buttonTacheAsynchrone

    }///onClick




    /*
      * AsyncTask<Params, Progress, Result>
	 */

    /*
    private class TacheAsynchrone extends AsyncTask<String, Integer, Integer> {



        @Override
        // ----------------------------
        protected Integer doInBackground(String... asParametres) {

            // String... parametre : nombre variable d'arguments

            // Se deplace dans un thread d'arriere-plan
            int liProgression;

            // Execute la tache en arriere-plan et maj de la barre de progression
            for (liProgression = 1; liProgression <= 100; liProgression++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                // Sans l'appel a cette methode l'UI n'est pas maj
                publishProgress(liProgression);
            } /// for

            // Renvoie la valeur a onPostExecute
            return liProgression - 1;
        } // / doInBackground


     @Override
        // ----------------------------
        protected void onProgressUpdate(Integer... aiProgressions) {
            // Synchronisation avec le thread de l'UI
            // MAJ de la barre de progression
            barreDeProgression.setProgress(aiProgressions[0]);
            textViewProgressionPourcentage.setText(Integer.toString(aiProgressions[0]) + " %");
        } // / onProgressUpdate




     /*
        @Override
        // -------------------------
        protected void onPostExecute(Integer aiResultat) {

                // Synchronisation avec le thread de l'UI
                // Affiche le resultat final
                barreDeProgression.setProgress(aiResultat);
                textViewProgressionPourcentage.setText(Integer.toString(aiResultat) +  " %");

        } // / onPostExecute



        @Override
        // -------------------------
        protected void onPostExecute(String asResultat) {
            // Synchronisation avec le thread de l'UI
            // Affiche le resultat final
            //barreDeProgression.setProgress(Integer.valueOf(asResultat));
            barreDeProgression.setProgress(100);
            textViewProgressionPourcentage.setText("100 %");
            textViewCSV.setText(asResultat);
        } // / onPostExecute
    } // / TacheAsynchrone

} /// class
}///TacheAsynchrone
*/


    /**
     *
     */
    private void initEvents() {
        // Liaison widget <--> Events
        buttonTacheAsynchrone.setOnClickListener(this);
    } /// initEvents

     @Override
    public void onClick(View v) {
        textViewCSV.setText("");
        textViewProgressionPourcentage.setText("");
        InputStream is = this.getResources().openRawResource(R.raw.communes);
        new TacheAsynchrone().execute(is);
    } /// onClick


    /*
    * AsyncTask<Params, Progress, Result>
    */
    private class TacheAsynchrone extends AsyncTask<InputStream, Integer, String> {
        @Override
        // ----------------------------
        protected String doInBackground(InputStream... isParametres) {
            // String... parametre : nombre variable d'arguments

            // Se deplace dans un thread d'arriere-plan
            int liProgressionPourcentage = 0;
            int liCompteurEnrs = 0;
            InputStream is = isParametres[0];
            StringBuilder lsbContenu = new StringBuilder();
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String lsLigne = "";
                String[] tChamps;
                lsLigne = br.readLine();
                while ((lsLigne = br.readLine()) != null) {
                    tChamps = lsLigne.split(";");
                    lsbContenu.append("[");
                    lsbContenu.append(tChamps[1]);
                    lsbContenu.append("] ");
                    lsbContenu.append(tChamps[0]);
                    lsbContenu.append("\n");
                    liCompteurEnrs++;
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
                isr.close();
                is.close();
            } catch (Exception e) {
                Log.e("Erreur", e.getMessage());
            }
            // Renvoie la valeur a onPostExecute
            Log.i("Contenu", lsbContenu.substring(0, 100));
            return lsbContenu.toString();
        } // / doInBackground



    @Override
    // ----------------------------
    protected void onProgressUpdate(Integer... aiProgressions) {
        // Synchronisation avec le thread de l'UI
        // MAJ de la barre de progression
        barreDeProgression.setProgress(aiProgressions[0]);
        textViewProgressionPourcentage.setText(Integer.toString(aiProgressions[0]) + " %");
        //editTextContenu.setText();
    } // / onProgressUpdate

        @Override
        // -------------------------
        protected void onPostExecute(String asResultat) {
            // Synchronisation avec le thread de l'UI
            // Affiche le resultat final
            //barreDeProgression.setProgress(Integer.valueOf(asResultat));
            barreDeProgression.setProgress(100);
            textViewProgressionPourcentage.setText("100 %");
            textViewCSV.setText(asResultat);
        } // / onPostExecute
    } // / TacheAsynchrone

} /// class






