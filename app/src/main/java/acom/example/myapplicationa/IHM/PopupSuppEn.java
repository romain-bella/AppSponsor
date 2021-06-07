package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Donner;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class PopupSuppEn extends  ActivityEntreprise{
    private EntrepriseDAO bddEn;
    private Intent intent;
    private int idEn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsuppen);
        intent = getIntent();
        bddEn = new EntrepriseDAO(getApplicationContext());
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.6));

        //recup extra
        /*TextView CoActuel = (TextView) findViewById(R.id.textView18);
        if (intent.hasExtra("rsEn")){
            CoActuel.setText(getIntent().getStringExtra("rsEn"));
        }*/



        //Suppression Entreprise
        Button suppEn = (Button) findViewById(R.id.suppCo);
        suppEn.setOnClickListener(suppressionEn);

        //modif Entreprise

        TextView textView11 = (TextView) findViewById(R.id.textView11);
        textView11.setText("Ancienne Raison Sociale: "+getIntent().getStringExtra("rsEn"));
        TextView textView13 = (TextView) findViewById(R.id.textView13);
        textView13.setText(" Ancienne Ville: "+getIntent().getStringExtra("villeEn"));
        TextView textView12 = (TextView) findViewById(R.id.textView12);
        textView12.setText(" Ancienne Rue: "+getIntent().getStringExtra("rueEn"));
        TextView textView19 = (TextView) findViewById(R.id.textView19);
        textView19.setText(" Ancien CP: "+getIntent().getStringExtra("cpEn"));
        TextView textView20 = (TextView) findViewById(R.id.textView20);
        textView20.setText(" Ancien telephone: "+getIntent().getStringExtra("telEn"));
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        textView14.setText(" Ancien mail: "+getIntent().getStringExtra("mailEn"));
        Button modifEn = (Button) findViewById(R.id.modifEn);
        EditText editText0 = (EditText) findViewById(R.id.searchFilterCo);
        EditText editText1 = (EditText) findViewById(R.id.editText2);
        EditText editText2 = (EditText) findViewById(R.id.editText1);
        EditText editText3 = (EditText) findViewById(R.id.editText4);
        EditText editText4 = (EditText) findViewById(R.id.editText5);
        EditText editText5 = (EditText) findViewById(R.id.editText3);
        modifEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bddEn.open();
                Entreprise uneE = new Entreprise(getIntent().getIntExtra("idEn", 0),editText0.getText().toString(),editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),editText5.getText().toString());
                bddEn.update(uneE);
                bddEn.close();
                Intent intent = new Intent (getApplicationContext(), ActivityEntreprise.class);
                startActivity(intent);
            }
        });

    }
    private View.OnClickListener suppressionEn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Correspondant unCorrespondant;
            if (intent != null){
                Entreprise uneEn = new Entreprise(getIntent().getIntExtra("idEn",0),getIntent().getStringExtra("rsEn"), getIntent().getStringExtra("villeEn"),getIntent().getStringExtra("rueEn"), getIntent().getStringExtra("cpEn"), getIntent().getStringExtra("telCo"),getIntent().getStringExtra("mailCo") );
                Log.v("OBJ", uneEn.toString());
                bddEn.open();
                bddEn.delete(uneEn);
                bddEn.close();
            }


            Intent intent = new Intent (getApplicationContext(), ActivityEntreprise.class);
            startActivity(intent);
        }
    };
}

