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

import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.R;

public class ActivityEntreprise extends MainActivity {

    private ListView listeEntreprise;
    private EntrepriseDAO bddEn;
    private Button boutonRetour, boutonAjout;
    private EditText searchFilterEn;
    private ArrayAdapter<Entreprise> listEntrepriseAdaptater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);

        //Bouton Retour
        boutonRetour = (Button) findViewById(R.id.buttonR2);
        boutonRetour.setOnClickListener(RetourListener);


        //Affichage list des Entreprise
        listeEntreprise = (ListView) findViewById(R.id.listEntreprise);
        bddEn = new EntrepriseDAO(this);
        bddEn.open();
        ArrayList<Entreprise> values = new ArrayList<Entreprise>();
        values = bddEn.read();
        listEntrepriseAdaptater = new ArrayAdapter<Entreprise>(this, android.R.layout.simple_list_item_1, values);
        listeEntreprise.setAdapter(listEntrepriseAdaptater);

        /*for (int i=0;i<=bddCo.compte();i++){
            values = values+bddCo.read(i);
        }*/

        bddEn.close();
        //PopUp Suppression
        listeEntreprise.setOnItemClickListener(suppressionEn);

        //Creation d'un nouveau correspondant
        boutonAjout = (Button) findViewById(R.id.boutonAjout);
        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityEntreprise.this,PopupAjoutEn.class));
            }
        });

        //filtre
        searchFilterEn = (EditText) findViewById(R.id.searchFilterCo);
        searchFilterEn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ActivityEntreprise.this).listEntrepriseAdaptater.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private AdapterView.OnItemClickListener suppressionEn = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ActivityEntreprise.this, PopupSuppEn.class);
            intent.putExtra("idEn", listEntrepriseAdaptater.getItem(position).getId_E());
            intent.putExtra ("rsEn", listEntrepriseAdaptater.getItem(position).getRaison_sociale());
            intent.putExtra ("villeEn", listEntrepriseAdaptater.getItem(position).getVille());
            intent.putExtra ("rueEn", listEntrepriseAdaptater.getItem(position).getRue());
            intent.putExtra ("cpEn", listEntrepriseAdaptater.getItem(position).getCP());
            intent.putExtra ("telEn",listEntrepriseAdaptater.getItem(position).getTelephone());
            intent.putExtra ("mailEn",listEntrepriseAdaptater.getItem(position).getMail());
            startActivity(intent);
        }
    };

    private View.OnClickListener RetourListener = v -> {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    };

}
