package fr.am.cinescope;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cinescope extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //private TextView textViewRubrique;
    //private ImageView imageViewLogo;
    private ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinescope);

        //textViewRubrique = (TextView)findViewById(R.id.textViewRubrique);
        //imageViewLogo = (ImageView)findViewById(R.id.imageViewLogo);
        liste =  (ListView) findViewById(R.id.liste);
        liste.setOnItemClickListener(this);

        String[] tRubrique = {"Tous les films","Box Office","Hit parade du public","Avis des critiques","Salle de Paris","Salle de peripherie"};

        // Recupere l'id de l'image image_1 ... en base decimale
        String[] tImages = new String[6];
        tImages[0] = String.valueOf(R.drawable.tous_films);
        tImages[1] = String.valueOf(R.drawable.box_office);
        tImages[2] = String.valueOf(R.drawable.hit_parade);
        tImages[3] = String.valueOf(R.drawable.avis_des_critiques);
        tImages[4] = String.valueOf(R.drawable.paris_cinema);
        tImages[5] = String.valueOf(R.drawable.banlieue);


        try {

            List<Map<String, String>> listeRubrique = new ArrayList<Map<String, String>>();
            Map<String, String> hm;

            for(int i = 0; i < tRubrique.length; i++) {
                hm = new HashMap<String, String>();

                hm.put("rubrique", tRubrique[i]);
                hm.put("image", tImages[i]);


                listeRubrique.add(hm);
            }

            SimpleAdapter sa = new SimpleAdapter(
                    this.getBaseContext(),
                    listeRubrique,
                    R.layout.image,
                    new String[] {"image","rubrique"},
                    new int[] { R.id.imageViewLogo, R.id.textViewRubrique}
            );

            liste.setAdapter(sa);

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erreur ? " + e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }///OnCreate



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Map<String, String> hm = (Map<String, String>) parent.getItemAtPosition(position);
        String lsChoix = hm.get("rubrique");
        Log.e("choix", lsChoix);

        Intent intention = null;

       if(lsChoix.equals("Tous les films")){
           Log.e("TLF",lsChoix);
           intention = new Intent (this, TousLesFilms.class);
       }
        if(lsChoix.equals("Box Office")){
            Log.e("BO",lsChoix);
            intention = new Intent (this, BoxOffice.class);
        }
        startActivity(intention);
    }///onItemClick

}///class
