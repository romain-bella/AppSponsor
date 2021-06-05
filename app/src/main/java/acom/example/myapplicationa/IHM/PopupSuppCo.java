package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.R;

public class PopupSuppCo extends  ActivityCorrespondant{
    private CorrespondantDAO bddCo;
    private Intent intent;
    private int idCo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsupp);
        intent = getIntent();
        bddCo = new CorrespondantDAO(getApplicationContext());
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.5));

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
}
