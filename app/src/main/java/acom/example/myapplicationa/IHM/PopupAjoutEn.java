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

public class PopupAjoutEn extends  ActivityEntreprise{
    private Button boutonAjoutEn;
    private EditText editText0, editText1, editText2, editText3, editText4, editText5;
    private EntrepriseDAO bddEn;
    private Entreprise nvE;
    private ArrayList<Entreprise> listEn;
    private Entreprise Entselectionner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupajouten);
        bddEn = new EntrepriseDAO(getApplicationContext());

        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.6));

        //Ajout
        editText0 = (EditText) findViewById(R.id.searchFilterCo);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        boutonAjoutEn = (Button) findViewById(R.id.nouveauCo);
        boutonAjoutEn.setOnClickListener(ajoutCoListener);

    }

    private View.OnClickListener ajoutCoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String rs = editText0.getText().toString();
            String ville = editText1.getText().toString();
            String rue = editText2.getText().toString();
            String cp = editText3.getText().toString();
            String Tel = editText4.getText().toString();
            String mail = editText5.getText().toString();
            bddEn.open();
            Entreprise laEn = new Entreprise(bddEn.last()+1,rs,ville,rue,cp, Tel,mail);
            bddEn.insert(laEn);
            bddEn.close();
            Intent intent = new Intent (getApplicationContext(), ActivityEntreprise.class);
            startActivity(intent);
        }
    };



}
