package fr.am.ecoute;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;



/**
 * Enregistrement et ecoute
 */
public class EnregistrementVoix extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private static final String LOG_TAG = "AudioRecordTest";
    private static String isNomFichier = null;

    private MediaRecorder mediaEnregistreur = null;
    private MediaPlayer mediaEcouteur = null;

    private ImageButton imageButtonEnregistrer;
    private ImageButton imageButtonSpeaker;
    private ImageButton imageButtonEcouter;
    private TextView textViewMessage;

    private boolean ibEnregistrement;
    private boolean ibEcoute;

    private Intent intention;


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.enregistrement_voix);

        initInterface();
        // Sur la SD
        isNomFichier = Environment.getExternalStorageDirectory().getAbsolutePath();
        // Nom du fichier
        isNomFichier += "/audiorecordtest.3gp";

        ibEnregistrement = false;
        ibEcoute = false;
    }///onCreate

    @Override
    public void onPause() {
        // Si une autre activite arrive au premier plan
        super.onPause();

        if (mediaEnregistreur != null) {
            mediaEnregistreur.release();
            mediaEnregistreur = null;
        }
        if (mediaEcouteur != null) {
            mediaEcouteur.release();
            mediaEcouteur = null;
        }
    }///onPause

    @Override
    public void onClick(View v) {

        if (v == imageButtonEnregistrer) {
            if (ibEnregistrement) {
                textViewMessage.setText("Arrêt de l'enregistrement");
                imageButtonEnregistrer.setImageResource(android.R.drawable.ic_btn_speak_now);
                imageButtonEnregistrer.setBackgroundColor(Color.GREEN);
                arreterEnregistrement();
            } else {
                textViewMessage.setText("Enregistrement");
                imageButtonEnregistrer.setImageResource(android.R.drawable.stat_notify_call_mute);
                imageButtonEnregistrer.setBackgroundColor(Color.RED);
                demarrerEnregistrement();
            }
            ibEnregistrement = !ibEnregistrement;
        }

        if (v == imageButtonEcouter) {
            if (ibEcoute) {
                textViewMessage.setText("Arrêt écoute enregistrement");
                imageButtonEcouter.setImageResource(android.R.drawable.ic_media_play);
                imageButtonEcouter.setBackgroundColor(Color.GREEN);
                arreterEcoute();
            } else {
                textViewMessage.setText("Ecoute enregistrement");
                imageButtonEcouter.setImageResource(android.R.drawable.ic_media_pause);
                imageButtonEcouter.setBackgroundColor(Color.RED);
                demarrerEcoute();
            }
            ibEcoute = !ibEcoute;
        }

        if(v == imageButtonSpeaker ){
            // --- Une Intention vide
            intention = new Intent();
            intention.setClass(this, PermissionsCheck.class);
            startActivity(intention);

            // --- On clot l'activite
            finish();

        }

    }///onClick

    private void demarrerEnregistrement() {
        mediaEnregistreur = new MediaRecorder();
        mediaEnregistreur.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaEnregistreur.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaEnregistreur.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaEnregistreur.setOutputFile(isNomFichier);

        try {
            mediaEnregistreur.prepare();
            mediaEnregistreur.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Echec de la préparation");
        }
    } // / demarrerEnregistrement

    private void arreterEnregistrement() {
        mediaEnregistreur.stop();
        mediaEnregistreur.release();
        mediaEnregistreur = null;
    } // / arreterEnregistrement

    private void demarrerEcoute() {
        mediaEcouteur = new MediaPlayer();
        try {
            mediaEcouteur.setOnCompletionListener(this);
            mediaEcouteur.setDataSource(isNomFichier);
            mediaEcouteur.prepare();
            mediaEcouteur.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Echec de la préparation");
        }
    } // / demarrerEcoute

    private void initInterface() {
        // Liaison widget <--> Attribut
        imageButtonEcouter = (ImageButton) findViewById(R.id.imageButtonEcouter);
        imageButtonEnregistrer = (ImageButton) findViewById(R.id.imageButtonEnregistrer);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        imageButtonSpeaker = (ImageButton) findViewById(R.id.imageButtonSpeaker);

        imageButtonEnregistrer.setOnClickListener(this);
        imageButtonEcouter.setOnClickListener(this);
        imageButtonSpeaker.setOnClickListener(this);


    } // / initInterface

    /**
     *
     */
    private void arreterEcoute() {
        mediaEcouteur.release();
        mediaEcouteur = null;
    } // / arreterEcoute

    @Override
    public void onCompletion(MediaPlayer mp) {
        // A la fin de l'ecoute
        imageButtonEcouter.setImageResource(android.R.drawable.ic_media_play);
        imageButtonEcouter.setBackgroundColor(Color.GREEN);
        ibEcoute = !ibEcoute;
        textViewMessage.setText("Lecture audio terminée");
    }///onCompletion
}///Class
