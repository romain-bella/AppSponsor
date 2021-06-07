package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.DAO.SaisonDAO;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class ActivitySaison extends MainActivity{
    private ListView listeSaison;
    private SaisonDAO bddSa;
    private Button boutonRetour, boutonAjout;
    private EditText searchFilterSa;
    private ArrayAdapter<Saison> listSaisonAdaptater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saison);

        //Bouton Retour
        boutonRetour = (Button) findViewById(R.id.buttonR2);
        boutonRetour.setOnClickListener(RetourListener);


        //Affichage list des Entreprise
        listeSaison = (ListView) findViewById(R.id.listEntreprise);
        bddSa = new SaisonDAO(this);
        bddSa.open();
        ArrayList<Saison> values = new ArrayList<Saison>();
        values = bddSa.read();
        listSaisonAdaptater = new ArrayAdapter<Saison>(this, android.R.layout.simple_list_item_1, values);
        listeSaison.setAdapter(listSaisonAdaptater);
        bddSa.close();
        //PopUp Suppression
        listeSaison.setOnItemClickListener(suppressionSa);

        //Creation d'un nouveau correspondant
        boutonAjout = (Button) findViewById(R.id.boutonAjout);
        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitySaison.this,PopupAjoutSa.class));
            }
        });

        //filtre
        searchFilterSa = (EditText) findViewById(R.id.searchFilterCo);
        searchFilterSa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ActivitySaison.this).listSaisonAdaptater.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private AdapterView.OnItemClickListener suppressionSa = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ActivitySaison.this, PopupSuppSa.class);
            intent.putExtra("idSa", listSaisonAdaptater.getItem(position).getId_saison());
            intent.putExtra ("dbSa", listSaisonAdaptater.getItem(position).getDateDebut());
            intent.putExtra ("dfSa", listSaisonAdaptater.getItem(position).getDateFin());
            startActivity(intent);
        }
    };

    private View.OnClickListener RetourListener = v -> {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    };
}
