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

import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.R;

public class ActivityPoste extends MainActivity{

    private ListView listePoste;
    private PosteDAO bddPo;
    private Button boutonRetour, boutonAjout;
    private EditText searchFilterPo;
    private ArrayAdapter<Poste> listPosteAdaptater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poste);

        //Bouton Retour
        boutonRetour = (Button) findViewById(R.id.buttonR2);
        boutonRetour.setOnClickListener(RetourListener);


        //Affichage list des Entreprise
        listePoste = (ListView) findViewById(R.id.listEntreprise);
        bddPo = new PosteDAO(this);
        bddPo.open();
        ArrayList<Poste> values = new ArrayList<Poste>();
        values = bddPo.read();
        listPosteAdaptater = new ArrayAdapter<Poste>(this, android.R.layout.simple_list_item_1, values);
        listePoste.setAdapter(listPosteAdaptater);
        bddPo.close();
        //PopUp Suppression
        listePoste.setOnItemClickListener(suppressionPo);

        //Creation d'un nouveau correspondant
        boutonAjout = (Button) findViewById(R.id.boutonAjout);
        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPoste.this,PopupAjoutPo.class));
            }
        });

        //filtre
        searchFilterPo = (EditText) findViewById(R.id.searchFilterCo);
        searchFilterPo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ActivityPoste.this).listPosteAdaptater.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private AdapterView.OnItemClickListener suppressionPo = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ActivityPoste.this, PopupSuppPo.class);
            intent.putExtra("idPo", listPosteAdaptater.getItem(position).getId_poste());
            intent.putExtra ("nomPo", listPosteAdaptater.getItem(position).getNom_poste());
            startActivity(intent);
        }
    };

    private View.OnClickListener RetourListener = v -> {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    };

}
