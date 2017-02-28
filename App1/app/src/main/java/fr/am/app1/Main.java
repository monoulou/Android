package fr.am.app1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends AppCompatActivity implements View.OnClickListener, OnFocusChangeListener {

    private EditText editTextPrenom;
    private EditText editTextNom;
    private Button buttonValider;
    private TextView textViewMessage;
    private Button buttonAnnuler;

    //private int comptNom = 0;
    //private int comptPrenom = 0;

    private String isTextOrigineNom;
    private String isTextOriginePrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextPrenom = (EditText) findViewById(R.id.editTextPrenom);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        buttonValider.setOnClickListener(this);
        buttonValider.setFocusable(true);
        buttonValider.setFocusableInTouchMode(true);
        buttonValider.requestFocus();

        buttonAnnuler.setOnClickListener(this);

        editTextPrenom.setOnFocusChangeListener(this);
        editTextNom.setOnFocusChangeListener(this);

        isTextOrigineNom = editTextNom.getText().toString();
        isTextOriginePrenom = editTextPrenom.getText().toString();
    }/// on create

    @Override
    public void onClick(View v) {
        if (v == buttonValider) {
            // Sans toString ça autobox (getText() renvoie un Editable)

            textViewMessage.setText(editTextPrenom.getText().toString() +" "+ editTextNom.getText().toString());

        }

        if (v == buttonAnnuler) {
            // Sans toString ça autobox (getText() renvoie un Editable)
            editTextPrenom.setText("");
            editTextNom.setText("");
            textViewMessage.setText("");
        }
    } /// onClick

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // --- Focus editTextPrenom
        if(v == editTextPrenom && hasFocus) {

            if (editTextPrenom.getText().toString().equals(isTextOriginePrenom)) {
                editTextPrenom.setText("");
                editTextPrenom.setTextColor(Color.BLACK);
                editTextPrenom.setTypeface(null, Typeface.NORMAL);
            } else if (editTextPrenom.getText().toString().equals("")) {
                editTextPrenom.setText(isTextOriginePrenom);
                editTextPrenom.setTextColor(Color.DKGRAY);
                editTextPrenom.setTypeface(null, Typeface.ITALIC);
            }

        } /// Focus editTextPrenom




        // --- Focus editTextNom
        if(v == editTextNom && hasFocus) {
            if (editTextNom.getText().toString().equals(isTextOrigineNom)) {
                editTextNom.setText("");
                editTextNom.setTextColor(Color.BLACK);
                editTextNom.setTypeface(null, Typeface.NORMAL);
            } else if (editTextNom.getText().toString().equals("")) {
                editTextNom.setText(isTextOrigineNom);
                editTextNom.setTextColor(Color.DKGRAY);
                editTextNom.setTypeface(null, Typeface.ITALIC);
            }
        } /// Focus editTextNom




    } /// onFocusChange


}/// Main
