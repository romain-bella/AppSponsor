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

import acom.example.myapplicationa.Adapter.EntrepriseAdapter;
import acom.example.myapplicationa.Adapter.PosteAdapter;
import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.R;

public class PopupAjoutCo extends  ActivityCorrespondant{
    private Button boutonAjoutCo;
    private EditText editText0, editText1, editText2, editText3;
    private Spinner postespinner, entreprisespinner;
    private CorrespondantDAO bddCo;
    private PosteDAO bddPo;
    private EntrepriseDAO bddEn;
    private Correspondant nvCorrespondant;
    private ArrayList<Poste> listPoste;
    private ArrayList<Entreprise> listEn;
    private PosteAdapter pAdapter;
    private EntrepriseAdapter eAdapter;
    private Entreprise Entselectionner;
    private Poste Posteselectionner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupajout);
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
        editText0 = (EditText) findViewById(R.id.searchFilterCo);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        boutonAjoutCo = (Button) findViewById(R.id.nouveauCo);
        boutonAjoutCo.setOnClickListener(ajoutCoListener);
        //Gestion spinner Postes
        postespinner = findViewById(R.id.postespinner);
        pAdapter = new PosteAdapter( getApplicationContext(), listPoste);
        postespinner.setAdapter(pAdapter);

        postespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Posteselectionner = (Poste) parent.getItemAtPosition(position);
                String clickednomposte = Posteselectionner.getNom_poste();
                Toast.makeText( PopupAjoutCo.this, clickednomposte, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Gestion spinner Entreprises
        entreprisespinner = findViewById(R.id.entreprisespinner);
        eAdapter = new EntrepriseAdapter(getApplicationContext(),listEn);
        entreprisespinner.setAdapter(eAdapter);

        entreprisespinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Entselectionner = (Entreprise) parent.getItemAtPosition(position);
                String clickednomEnt = Entselectionner.getRaison_sociale();
                Toast.makeText(PopupAjoutCo.this, clickednomEnt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));


    }

    private View.OnClickListener ajoutCoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nom = editText0.getText().toString();
            String prenom = editText1.getText().toString();
            String Tel = editText2.getText().toString();
            String mail = editText3.getText().toString();
            //uneEntreprise = new Entreprise()
            //nvCorrespondant = new Correspondant(nom, prenom, Tel, mail, )
            bddCo.open();
            Correspondant leCo = new Correspondant(bddCo.compte()+1,nom,prenom,Tel,mail,Entselectionner,Posteselectionner);
            bddCo.insert(leCo);
            bddCo.close();
            Intent intent = new Intent (getApplicationContext(), ActivityCorrespondant.class);
            startActivity(intent);
        }
    };

    private void initList(){
        bddPo.open();
        listPoste = new ArrayList<>();
        for (int i=1;i<=bddPo.compte();i++){
           listPoste.add(new Poste(bddPo.read(i).getId_poste(), bddPo.read(i).getNom_poste()));
        }
        bddPo.close();
        bddEn.open();
        listEn = new ArrayList<>();
        for (int n=1;n<=bddEn.compte();n++){
            listEn.add(new Entreprise(bddEn.read(n).getId_E(), bddEn.read(n).getRaison_sociale(), bddEn.read(n).getVille(), bddEn.read(n).getRue(), bddEn.read(n).getCP(), bddEn.read(n).getTelephone(), bddEn.read(n).getMail()));
        }
        bddEn.close();
    }
}
