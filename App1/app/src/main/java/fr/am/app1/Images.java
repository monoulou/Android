package fr.am.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Images extends Activity implements OnClickListener {

    private Button buttonSuivant;
    private Button buttonRetour;
    private ImageView imageView1;

    @Override
    // -----------------
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images);

        initInterface();
    } // / onCreate()

    private void initInterface() {
        buttonSuivant = (Button) findViewById(R.id.buttonSuivant);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);

        buttonSuivant.setOnClickListener(this);
        buttonRetour.setOnClickListener(this);

        imageView1 = (ImageView) findViewById(R.id.imageView1);

        buttonRetour.setEnabled(false);
    } // / initInterface

    @Override
    public void onClick(View v) {
        if (v == buttonRetour) {
            imageView1.setImageResource(R.drawable.paysage_1);
            buttonSuivant.setEnabled(true);
            buttonRetour.setEnabled(false);
        }

        if (v == buttonSuivant) {
            imageView1.setImageResource(R.drawable.paysage_2);
            buttonSuivant.setEnabled(false);
            buttonRetour.setEnabled(true);
        }
    } // / onClick

} // / classe
