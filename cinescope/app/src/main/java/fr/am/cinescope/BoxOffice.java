package fr.am.cinescope;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BoxOffice extends AppCompatActivity {

    private TextView textViewBoxOffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_office);

        textViewBoxOffice =  (TextView) findViewById(R.id.textViewBoxOffice);
    }///onCreate
}///BoxOffice
