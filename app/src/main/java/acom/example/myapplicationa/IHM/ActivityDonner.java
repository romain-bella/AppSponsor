package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.DAO.DonnerDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Donner;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class ActivityDonner extends MainActivity{
    private ListView listeD;
    private DonnerDAO bddD;
    private Button boutonRetour, boutonAjout;
    private EditText searchFilterD;
    private ArrayAdapter<Donner> listDonnerAdaptater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner);

        //Bouton Retour
        boutonRetour = (Button) findViewById(R.id.buttonR);
        boutonRetour.setOnClickListener(RetourListener);


        //Affichage list des Correspondant
        listeD = (ListView) findViewById(R.id.listCorrespondant);
        bddD = new DonnerDAO (this);
        bddD.open();
        ArrayList<Donner> values = new ArrayList<Donner>();
        values = bddD.read();
        listDonnerAdaptater = new ArrayAdapter<Donner>(this, android.R.layout.simple_list_item_1, values);
        listeD.setAdapter(listDonnerAdaptater);

        bddD.close();
        //PopUp Suppression
        listeD.setOnItemClickListener(suppressionD);

        //Creation d'un nouveau correspondant
        boutonAjout = (Button) findViewById(R.id.boutonAjout);
        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityDonner.this,PopupAjoutDo.class));
            }
        });

        //filtre
        searchFilterD = (EditText) findViewById(R.id.searchFilterCo);
        searchFilterD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ActivityDonner.this).listDonnerAdaptater.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    private AdapterView.OnItemClickListener suppressionD = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ActivityDonner.this, PopupSuppDo.class);
            intent.putExtra("idSa",listDonnerAdaptater.getItem(position).getSo().getId_saison());
            intent.putExtra("ddSa",listDonnerAdaptater.getItem(position).getSo().getDateDebut());
            intent.putExtra("dfSa",listDonnerAdaptater.getItem(position).getSo().getDateFin());
            intent.putExtra("idCo", listDonnerAdaptater.getItem(position).getCo().getId_correspondant());
            intent.putExtra ("nomCo", listDonnerAdaptater.getItem(position).getCo().getNom_co());
            intent.putExtra ("telCo", listDonnerAdaptater.getItem(position).getCo().getTelephone());
            intent.putExtra ("mailCo", listDonnerAdaptater.getItem(position).getCo().getMail());
            intent.putExtra ("prenomCo", listDonnerAdaptater.getItem(position).getCo().getPrenom_co());
            intent.putExtra ("posteid",listDonnerAdaptater.getItem(position).getCo().getUnPoste().getId_poste());
            intent.putExtra ("postenom",listDonnerAdaptater.getItem(position).getCo().getUnPoste().getNom_poste());
            intent.putExtra("entid", listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getId_E());
            intent.putExtra("entnom",listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getRaison_sociale());
            intent.putExtra("entville",listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getVille());
            intent.putExtra("entrue",listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getRue());
            intent.putExtra("entCp",listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getCP());
            intent.putExtra("enttel",listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getTelephone());
            intent.putExtra("entmail",listDonnerAdaptater.getItem(position).getCo().getUneEntreprise().getMail());
            intent.putExtra("mDo",listDonnerAdaptater.getItem(position).getMontant());
            intent.putExtra("ddDo",listDonnerAdaptater.getItem(position).getDateD());
            startActivity(intent);
        }
    };

    private View.OnClickListener RetourListener = v -> {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    };
}
