package fr.am.cinescope;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TousLesFilms extends AppCompatActivity {

    private TextView textViewTousFilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tous_les_films);

        textViewTousFilms =  (TextView) findViewById(R.id.textViewTousFilms);

    }///onCreate
}///TousLesFilms
