package fr.am.webandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetJSON extends AppCompatActivity {

    private TextView textViewJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_json);

        textViewJSON = (TextView) findViewById(R.id.textViewJSON);

        //new TacheAsynchrone().execute("http://172.026.10.88/","pays.json", "nom_pays");
        new TacheAsynchrone().execute("http://172.026.10.88:80800","/CinesCope2014FO/JsonPays", "nom_pays");
    }///onCreate


    private class TacheAsynchrone extends AsyncTask<String, Integer, String> {

        private String isChamp;
        private String isType;


        @Override
        // ----------------------------------
        protected String doInBackground(String... asParametres) {


            StringBuilder lsb = new StringBuilder();
            String lsURL;
            String lsRessource;


            lsURL = asParametres[0];
            lsRessource = asParametres[1];
            isChamp = asParametres[2];
            //isType = asParametres[3];

            URL urlConnection = null;
            HttpURLConnection httpConnection = null;





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
                    lsb.append(lsLigne);

                }
                br.close();
                is.close();

            }catch(IOException e){
                lsb.append(e.getMessage());
            }finally {
                // Deconnexion
                httpConnection.disconnect();
            }
            return lsb.toString();
        }///doInBackground


        protected void onPostExecute(String asResultat) {

            StringBuilder lsb = new StringBuilder();
            List<String> listePays = AnalyseurJSON.getChampFromJSONArray(asResultat, isChamp);
            for(int i = 0 ; i < listePays.size(); i++){
                lsb.append(listePays.get(i));
                lsb.append("\n");
            }
            textViewJSON.setText(lsb.toString());
        } /// onPostExecute
    }///TacheAsynchrone
}///class
