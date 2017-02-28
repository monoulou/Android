package fr.am.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewLivres extends AppCompatActivity implements OnItemClickListener {

    private ListView listViewLivres;
    private String[] tLivres = { "L'insoutenable", "Risibles amours", "Cent ans de solitude", "Eugénie Grandet", "L'éducation sentimentale" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_livres);

        listViewLivres=(ListView)findViewById(R.id.listViewLivres);

        listViewLivres.setOnItemClickListener(this);

        ArrayAdapter<String> aaListeLivres = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tLivres);
        listViewLivres.setAdapter(aaListeLivres);
        listViewLivres.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
    }
}
