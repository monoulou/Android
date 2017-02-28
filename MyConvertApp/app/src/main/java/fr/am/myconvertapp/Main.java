package fr.am.myconvertapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity implements OnClickListener {

    private EditText editTextCours;
    private EditText editTextMontant;
    private Button buttonValider;
    private TextView textViewMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editTextCours = (EditText) findViewById(R.id.editTextCours);
        editTextMontant = (EditText) findViewById(R.id.editTextMontant);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        buttonValider.setOnClickListener(this);
    }///onCreate

    public void onClick(View v) {

        //Déclaration des variables
        double montant;
        double cours;
        double rst;
        //Fin de déclaration

        if (v == buttonValider) {
            // Sans toString ça autobox (getText() renvoie un Editable)
            montant = Double.parseDouble(editTextMontant.getText().toString());
            cours = Double.parseDouble(editTextCours.getText().toString());
            rst = montant*cours;


            textViewMessage.setText(String.valueOf(rst));
        }
    } // / onClick
}/// Main
