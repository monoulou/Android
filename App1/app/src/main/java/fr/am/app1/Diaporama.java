package fr.am.app1;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


public class Diaporama extends Activity implements OnClickListener {

    private Button buttonDebut;
    private Button buttonRetour;
    private Button buttonSuivant;
    private Button buttonFin;
    private ImageView imageView1;
    private int iiCompteur = 1;
    private TextView textCompteur;
    private Button buttonTest;
    //private TextView textViewMessage;




    @Override
    // -----------------
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaporama);

        initInterface();

    } // / onCreate()

    private void initInterface() {

        buttonDebut = (Button) findViewById(R.id.buttonDebut);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);
        buttonSuivant = (Button) findViewById(R.id.buttonSuivant);
        buttonFin = (Button) findViewById(R.id.buttonFin);
        buttonTest = (Button) findViewById(R.id.buttonTest);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        textCompteur = (TextView)findViewById(R.id.textCompteur);
        //textViewMessage = (TextView) findViewById(R.id.textViewMessage);




        buttonDebut.setOnClickListener(this);
        buttonRetour.setOnClickListener(this);
        buttonSuivant.setOnClickListener(this);
        buttonFin.setOnClickListener(this);
        buttonTest.setOnClickListener(this);





        buttonRetour.setEnabled(false);
        buttonFin.setEnabled(false);


    } // / initInterface

    @Override
    public void onClick(View v) {
        if (v == buttonDebut) {
            iiCompteur = 1;
        }
        if (v == buttonRetour) {
            iiCompteur--;
        }
        if (v == buttonSuivant) {
            iiCompteur++;
        }
        if (v == buttonFin) {
            iiCompteur = 5;
        }

        buttonDebut.setEnabled(iiCompteur > 1);
        buttonRetour.setEnabled(iiCompteur > 1);
        buttonSuivant.setEnabled(iiCompteur < 5);
        buttonFin.setEnabled(iiCompteur < 5);

        switch (iiCompteur) {
            case 1:
                imageView1.setImageResource(R.drawable.paysage_1);
                break;
            case 2:
                imageView1.setImageResource(R.drawable.paysage_2);
                break;
            case 3:
                imageView1.setImageResource(R.drawable.paysage_3);
                break;
            case 4:
                imageView1.setImageResource(R.drawable.paysage_4);
                break;
            case 5:
                imageView1.setImageResource(R.drawable.paysage_5);
                break;

            default:
                break;
        }

        textCompteur.setText( Integer.valueOf(iiCompteur) + " sur 5");


        if(v == buttonTest) {

            String lsTitre = "Alerte";
            String lsMessage = "Appliquez !";

            DialogInterface.OnClickListener ecouteur = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int codeBouton) {

                    // -1
                    if (codeBouton == Dialog.BUTTON_POSITIVE) {
                        Toast.makeText(getBaseContext(), "Saisies validées !!!", Toast.LENGTH_LONG).show();
                    }
                    // -2
                    if (codeBouton == Dialog.BUTTON_NEGATIVE) {
                        Toast.makeText(getBaseContext(), "Saisies annulées !!!", Toast.LENGTH_LONG).show();
                    }
                }
            };

            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle(lsTitre);
            ad.setMessage(lsMessage);
            ad.setNegativeButton("Annuler", ecouteur);
            ad.setPositiveButton("OK", ecouteur);
            ad.show();

        }/// buttonTest

    } // / onClick

} // / classe

