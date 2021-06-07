package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import acom.example.myapplicationa.Adapter.EntrepriseAdapter;
import acom.example.myapplicationa.Adapter.PosteAdapter;
import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Donner;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class PopupSuppCo extends  ActivityCorrespondant{
    private CorrespondantDAO bddCo;
    private PosteDAO bddPo;
    private EntrepriseDAO bddEn;
    private Intent intent;
    private int idCo;
    private ArrayList<Poste> listPoste;
    private ArrayList<Entreprise> listEn;
    private Entreprise Entselectionner;
    private Poste Posteselectionner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsupp);
        intent = getIntent();
        bddCo = new CorrespondantDAO(getApplicationContext());
        bddPo = new PosteDAO(getApplicationContext());
        bddEn = new EntrepriseDAO(getApplicationContext());
        initList();
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.6));

        //recup extra
        String Co = "";
        if (intent.hasExtra("nomCo")){
            Co= Co + getIntent().getStringExtra("nomCo");
        }
        if (intent.hasExtra("prenomCo")){
            Co = Co + getIntent().getStringExtra("prenomCo");
        }
        idCo = 0;
        if (intent.hasExtra("idCo")){
            idCo= getIntent().getIntExtra("idCo",0);
        }
        TextView CoActuel = (TextView) findViewById(R.id.textView18);
        CoActuel.setText(Co);
        //Suppression Correspondant
        Button suppCo = (Button) findViewById(R.id.suppCo);
        suppCo.setOnClickListener(suppressionCo);


        //modif Correspondant
        TextView MoActuel = (TextView) findViewById(R.id.textView11);
        MoActuel.setText("Nom Actuel: "+getIntent().getStringExtra("nomCo"));
        TextView DdActuel = (TextView) findViewById(R.id.textView12);
        DdActuel.setText("Prenom actuel: "+getIntent().getStringExtra("prenomCo"));
        TextView SaActuel = (TextView) findViewById(R.id.textView13);
        SaActuel.setText("Telephone Actuel: "+getIntent().getStringExtra("telCo"));
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        textView14.setText("mail Actuel: "+getIntent().getStringExtra("mailCo")+" "+getIntent().getStringExtra("nomCo"));
        TextView textView15 = (TextView) findViewById(R.id.textView15);
        textView15.setText("Poste Actuel: "+getIntent().getStringExtra("postenom"));
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        textView9.setText("L'entreprise Actuelle: "+getIntent().getStringExtra("entnom"));

        EditText editText0 = (EditText) findViewById(R.id.searchFilterCo );
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        //Gestion spinner Entreprises
        //Gestion spinner Postes
        Spinner postespinner = (Spinner) findViewById(R.id.postespinner);
        Spinner entreprisespinner = (Spinner) findViewById(R.id.postespinner);


        PosteAdapter pAdapter = new PosteAdapter( getApplicationContext(), listPoste);
        postespinner.setAdapter(pAdapter);

        postespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Posteselectionner = (Poste) parent.getItemAtPosition(position);
                int clickedidposte = Posteselectionner.getId_poste();
                Toast.makeText( PopupSuppCo.this, ""+clickedidposte, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        entreprisespinner = findViewById(R.id.entreprisespinner);
        EntrepriseAdapter eAdapter = new EntrepriseAdapter(getApplicationContext(),listEn);
        entreprisespinner.setAdapter(eAdapter);

        entreprisespinner.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Entselectionner = (Entreprise) parent.getItemAtPosition(position);
                int clickedidEnt = Entselectionner.getId_E();
                Toast.makeText(PopupSuppCo.this, ""+clickedidEnt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));
        Button buttonM = (Button) findViewById(R.id.buttonM);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bddCo.open();

                Correspondant unCo = new Correspondant(getIntent().getIntExtra("idCo",0),editText0.getText().toString(), editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),Entselectionner, Posteselectionner);

                bddCo.update(unCo);
                bddCo.close();
                Intent intent = new Intent (getApplicationContext(), ActivityCorrespondant.class);
                startActivity(intent);
            }
        });
    }
    private View.OnClickListener suppressionCo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Correspondant unCorrespondant;
            if (intent != null){
                Entreprise uneE = new Entreprise(getIntent().getIntExtra("entid", 0),getIntent().getStringExtra("entnom"),getIntent().getStringExtra("entville"),getIntent().getStringExtra("entrue"),getIntent().getStringExtra("entCp"),getIntent().getStringExtra("enttel"),getIntent().getStringExtra("entmail"));
                Poste unP = new Poste(getIntent().getIntExtra("posteid",0), getIntent().getStringExtra("postnom"));
                Correspondant unCo = new Correspondant(getIntent().getIntExtra("idCo",0),getIntent().getStringExtra("nomCo"), getIntent().getStringExtra("prenomCo"),getIntent().getStringExtra("telCo"),getIntent().getStringExtra("mailCo"),uneE, unP);
                Log.v("OBJ", unCo.toString());
                bddCo.open();
                bddCo.delete(unCo);
                bddCo.close();
            }


            Intent intent = new Intent (getApplicationContext(), ActivityCorrespondant.class);
            startActivity(intent);
        }
    };
    private void initList(){
        bddPo.open();
        listPoste = new ArrayList<>();
        listPoste = bddPo.read();
        /*for (int i=1;i<=bddPo.compte();i++){
           listPoste.add(new Poste(bddPo.read(i).getId_poste(), bddPo.read(i).getNom_poste()));
        }*/
        bddPo.close();
        bddEn.open();
        listEn = new ArrayList<>();
        listEn = bddEn.read();
        /*for (int n=1;n<=bddEn.compte();n++){
            listEn.add(new Entreprise(bddEn.read(n).getId_E(), bddEn.read(n).getRaison_sociale(), bddEn.read(n).getVille(), bddEn.read(n).getRue(), bddEn.read(n).getCP(), bddEn.read(n).getTelephone(), bddEn.read(n).getMail()));
        }*/
        bddEn.close();
    }

}
