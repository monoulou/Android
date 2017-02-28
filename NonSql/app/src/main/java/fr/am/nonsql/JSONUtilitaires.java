package fr.am.nonsql;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;
/**
 * Created by formation on 21/02/2017.
 */
public class JSONUtilitaires {

    /**
     * @param is
     * @return
     */
    public static JSONArray jsonIS2JsonArray(InputStream is) throws JSONException {

        /*
        Renvoie un tableau d'objets JSON
        Le tableau est une "copie" du fichier
         */

        JSONArray tableauJSON = null;

        StringBuilder lsbContenu = new StringBuilder();

        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String lsLigne = "";
            while ((lsLigne = br.readLine()) != null) {
                lsbContenu.append(lsLigne);
                lsbContenu.append("\n");
            }
            br.close();
            isr.close();
            is.close();

            // Object 2 JSONArray
            tableauJSON = new JSONArray(lsbContenu.toString());

        } catch (JSONException e) {
            JSONObject objetErreur = new JSONObject();
            objetErreur.put("Erreur JSON", objetErreur);
            tableauJSON.put(objetErreur);
        } catch (IOException e) {
            JSONObject objetErreur = new JSONObject();
            objetErreur.put("Erreur IO", objetErreur);
            tableauJSON.put(objetErreur);
        }
        return tableauJSON;
    } /// jsonIS2JsonArray

    /**
     *
     * @param contexte
     * @param ObjetJson
     * @param nomFichier
     * @return
     */
    public static boolean insert(Context contexte, JSONObject ObjetJson, String nomFichier){
        /*
        Renvoie "true" si ecriture dans le fichier
        */
        try{
            InputStream is = contexte.openFileInput(nomFichier);
            JSONArray tableau = jsonIS2JsonArray(is);
            tableau.put(ObjetJson);

            String s = tableau.toString();

            OutputStream os = contexte.openFileOutput(nomFichier, Context.MODE_PRIVATE);

            os.write(s.getBytes());

            is.close();
            os.close();

            Log.e("INSERT", "OK");



        }catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("erreur io", e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.e("erreur io", e.getMessage());
        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.e("erreur JSON", e.getMessage());
        }


        return true;
    }///insert

    public static void delete(Context contexte, JSONObject ObjetJson, String nomFichier){
            try{


                InputStream is = contexte.openFileInput(nomFichier);
                JSONArray tableau = jsonIS2JsonArray(is);

                //String Contenu = tableau.toString();
                //Initialisation d'un index à -1
                int indexJson = -1;
                //Boucle sur le tableau
                for (int i = 0; i <tableau.length(); i++) {
                    if( tableau.getJSONObject(i).get("iso2").toString().equals(ObjetJson.get("iso2").toString())){
                        indexJson = i;
                        Log.e("","");
                    }///endif
                }///endfor

                tableau.remove(indexJson);

                String s = tableau.toString();

                OutputStream os = contexte.openFileOutput(nomFichier, Context.MODE_PRIVATE);

                os.write(s.getBytes());

                is.close();
                os.close();

                Log.e("DELETE", "OK");



            }catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("erreur io", e.getMessage());
            }
            catch (IOException e) {
                e.printStackTrace();
                Log.e("erreur io", e.getMessage());
            }
            catch (JSONException e) {
                e.printStackTrace();
                Log.e("erreur JSON", e.getMessage());
            }
    }///delete
}///class
