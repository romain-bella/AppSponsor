package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import acom.example.myapplicationa.Adapter.CorrespondantAdapter;
import acom.example.myapplicationa.Adapter.EntrepriseAdapter;
import acom.example.myapplicationa.Adapter.PosteAdapter;
import acom.example.myapplicationa.Adapter.SaisonAdapter;
import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.DAO.DonnerDAO;
import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.DAO.SaisonDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Donner;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class PopupAjoutDo extends ActivityDonner{
    private Button boutonAjoutDo;
    private EditText editText2, editText3;
    private Spinner correspondantspinner, saisonspinner;
    private DonnerDAO bddDo;
    private SaisonDAO bddSa;
    private CorrespondantDAO bddCo;
    private PosteDAO bddPo;
    private EntrepriseDAO bddEn;
    private ArrayList<Correspondant> listCo;
    private ArrayList<Saison> listSa;
    private CorrespondantAdapter cAdapter;
    private SaisonAdapter sAdapter;
    private Saison Saselectionner;
    private Correspondant Coselectionner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupajoutdo);
        bddDo = new DonnerDAO(getApplicationContext());
        bddSa = new SaisonDAO(getApplicationContext());
        bddCo = new CorrespondantDAO(getApplicationContext());
        bddPo = new PosteDAO(getApplicationContext());
        bddEn = new EntrepriseDAO(getApplicationContext());
        initList();
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.5));

        //Ajout
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        boutonAjoutDo = (Button) findViewById(R.id.nouveauCo);
        boutonAjoutDo.setOnClickListener(ajoutDoListener);
        //Gestion spinner saison
        saisonspinner = findViewById(R.id.saisonspinner);
        sAdapter = new SaisonAdapter( getApplicationContext(), listSa);
        saisonspinner.setAdapter(sAdapter);

        saisonspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Saselectionner = (Saison) parent.getItemAtPosition(position);
                int clickedidS = Saselectionner.getId_saison();
                Toast.makeText( PopupAjoutDo.this, ""+clickedidS, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Gestion spinner Entreprises
        correspondantspinner = findViewById(R.id.correspondantspinner);
        cAdapter = new CorrespondantAdapter(getApplicationContext(),listCo);
        correspondantspinner.setAdapter(cAdapter);

        correspondantspinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Coselectionner = (Correspondant) parent.getItemAtPosition(position);
                int clickedidC = Coselectionner.getId_correspondant();
                Toast.makeText(PopupAjoutDo.this, ""+clickedidC, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));


    }

    private View.OnClickListener ajoutDoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int montant = Integer.parseInt(editText2.getText().toString());
            String date = editText3.getText().toString();
            //uneEntreprise = new Entreprise()
            //nvCorrespondant = new Correspondant(nom, prenom, Tel, mail, )
            bddDo.open();
            Donner leD = new Donner(Saselectionner, Coselectionner ,montant,date);
            bddDo.insert(leD);
            bddDo.close();
            Intent intent = new Intent (getApplicationContext(), ActivityDonner.class);
            startActivity(intent);
        }
    };

    private void initList(){
        bddCo.open();
        listCo = new ArrayList<>();
        listCo = bddCo.read();

        bddCo.close();
        bddSa.open();
        listSa = new ArrayList<>();
        listSa = bddSa.read();

        bddSa.close();
    }
}
