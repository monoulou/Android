package fr.am.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListeViewAuteurs extends AppCompatActivity implements OnItemClickListener {

    private ListView listViewAuteurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_view_auteurs);

        listViewAuteurs = (ListView) findViewById(R.id.listViewAuteurs);

        listViewAuteurs.setOnItemClickListener(this);

    }/// onCreate

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
    }/// onItemClick
}/// class
