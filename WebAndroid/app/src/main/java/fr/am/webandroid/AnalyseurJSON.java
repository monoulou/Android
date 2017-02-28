package fr.am.webandroid;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

/**
 * Created by Administrateur on 14/02/2017.
 */
public class AnalyseurJSON {
    /**
     * @param asNomChamp
     * @return
     */
    public static List<String> getChampFromJSONArray(String asContenu, String asNomChamp) {
        List<String> liste = new ArrayList();

        try {
            // String 2 JSONArray
            JSONArray tableauJSON = new JSONArray(asContenu);
            // Dé-sérialisation
            JSONObject objetJSON;
            String[] t = new String[tableauJSON.length()];
            for (int i = 0; i < tableauJSON.length(); i++) {
                objetJSON = (JSONObject) tableauJSON.get(i);
                liste.add(objetJSON.get(asNomChamp).toString());
            }
        } catch (JSONException e) {
            liste.add(e.getMessage());
        } finally {
        }

        return liste;
    } /// getChampFromJSONArray


    /**
     * @param asContenu
     * @param asNomChamp
     * @return
     */
    public static List<String> getChampFromJSONObject(String asContenu, String asNomChamp) {
        List<String> liste = new ArrayList();

        try {
            // String 2 JSONObject
            //JSONObject objectJSON = new JSONObject("{\"cp\":\"24200\",\"nom_ville\":\"Sarlat\"}");
            Log.e("json", "*" + asContenu + "*");
            JSONObject objectJSON = new JSONObject(asContenu);
            liste.add(objectJSON.get(asNomChamp).toString());
        } catch (JSONException e) {
            liste.add(e.getMessage());
        } finally {
        }

        return liste;
    } /// getChampFromJSONObject
}///class
