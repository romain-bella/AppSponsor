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
import acom.example.myapplicationa.DAO.DonnerDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Donner;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class PopupSuppDo extends ActivityDonner{
    private DonnerDAO bddDo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsuppdo);
        intent = getIntent();
        bddDo = new DonnerDAO(getApplicationContext());
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.5));

        //recup extra
        String Do = "";
        if (intent.hasExtra("nomCo")){
            Do= Do + getIntent().getStringExtra("nomCo")+ " "+getIntent().getStringExtra("entnom");
        }



        TextView DoActuel = (TextView) findViewById(R.id.textView18);
        DoActuel.setText(Do);
        //Suppression Correspondant
        Button suppDo = (Button) findViewById(R.id.suppCo);
        suppDo.setOnClickListener(suppressionDo);
        double d = getIntent().getDoubleExtra("mDo",0);
        Log.v("blabla",""+d);


        //modif Correspondant
        TextView MoActuel = (TextView) findViewById(R.id.textView13);
        MoActuel.setText("Montant Actuel: "+getIntent().getDoubleExtra("mDo",0));
        TextView DdActuel = (TextView) findViewById(R.id.textView14);
        DdActuel.setText("Date de Donnation actuel: "+getIntent().getStringExtra("ddDo"));
        TextView SaActuel = (TextView) findViewById(R.id.textView15);
        SaActuel.setText("Saison Actuelle: "+getIntent().getStringExtra("ddSa")+"-"+getIntent().getStringExtra("dfSa"));
        TextView CoActuel = (TextView) findViewById(R.id.textView9);
        CoActuel.setText("Correspondant Actuel: "+getIntent().getStringExtra("entnom")+" "+getIntent().getStringExtra("nomCo"));
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        Button buttonM = (Button) findViewById(R.id.buttonM);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bddDo.open();
                Entreprise uneE = new Entreprise(getIntent().getIntExtra("entid", 0),getIntent().getStringExtra("entnom"),getIntent().getStringExtra("entville"),getIntent().getStringExtra("entrue"),getIntent().getStringExtra("entCp"),getIntent().getStringExtra("enttel"),getIntent().getStringExtra("entmail"));
                Poste unP = new Poste(getIntent().getIntExtra("posteid",0), getIntent().getStringExtra("postnom"));
                Correspondant unCo = new Correspondant(getIntent().getIntExtra("idCo",0),getIntent().getStringExtra("nomCo"), getIntent().getStringExtra("prenomCo"),getIntent().getStringExtra("telCo"),getIntent().getStringExtra("mailCo"),uneE, unP);
                Saison uneSa = new Saison(getIntent().getIntExtra("idSa",0),getIntent().getStringExtra("ddSa"),getIntent().getStringExtra("dfSa"));
                Donner unDo = new Donner (uneSa, unCo, Float.parseFloat(editText2.getText().toString()),editText3.getText().toString());
                bddDo.update(unDo);
                bddDo.close();
                Intent intent = new Intent (getApplicationContext(), ActivityDonner.class);
                startActivity(intent);
            }
        });

    }
    private View.OnClickListener suppressionDo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Donner unD;
            if (intent != null){
                Entreprise uneE = new Entreprise(getIntent().getIntExtra("entid", 0),getIntent().getStringExtra("entnom"),getIntent().getStringExtra("entville"),getIntent().getStringExtra("entrue"),getIntent().getStringExtra("entCp"),getIntent().getStringExtra("enttel"),getIntent().getStringExtra("entmail"));
                Poste unP = new Poste(getIntent().getIntExtra("posteid",0), getIntent().getStringExtra("postnom"));
                Correspondant unCo = new Correspondant(getIntent().getIntExtra("idCo",0),getIntent().getStringExtra("nomCo"), getIntent().getStringExtra("prenomCo"),getIntent().getStringExtra("telCo"),getIntent().getStringExtra("mailCo"),uneE, unP);
                Saison uneSa = new Saison(getIntent().getIntExtra("idSa",0),getIntent().getStringExtra("ddSa"),getIntent().getStringExtra("dfSa"));
                Donner unDo = new Donner (uneSa, unCo, getIntent().getIntExtra("mDo",0),getIntent().getStringExtra("ddDo"));
                Log.v("OBJ", unCo.toString());
                bddDo.open();
                bddDo.delete(unDo);
                bddDo.close();
            }


            Intent intent = new Intent (getApplicationContext(), ActivityDonner.class);
            startActivity(intent);
        }
    };
}
