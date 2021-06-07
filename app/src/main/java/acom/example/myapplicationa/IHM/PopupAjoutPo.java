package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.R;

public class PopupAjoutPo extends ActivityPoste{
    private Button boutonAjoutPo;
    private EditText editText0;
    private PosteDAO bddPo;
    private Poste unPo;
    private ArrayList<Poste> listPo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupajoutpo);
        bddPo = new PosteDAO(getApplicationContext());

        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.3));

        //Ajout
        editText0 = (EditText) findViewById(R.id.searchFilterCo);
        boutonAjoutPo = (Button) findViewById(R.id.nouveauCo);
        boutonAjoutPo.setOnClickListener(ajoutCoListener);

    }

    private View.OnClickListener ajoutCoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nom = editText0.getText().toString();
            bddPo.open();
            Poste lePo = new Poste(bddPo.last()+1,nom);
            bddPo.insert(lePo);
            bddPo.close();
            Intent intent = new Intent (getApplicationContext(), ActivityPoste.class);
            startActivity(intent);
        }
    };
}
