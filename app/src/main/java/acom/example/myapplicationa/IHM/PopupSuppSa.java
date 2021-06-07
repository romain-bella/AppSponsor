package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.DAO.SaisonDAO;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class PopupSuppSa extends ActivitySaison{
    private SaisonDAO bddSa;
    private Intent intent;
    private int idSa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsuppsa);
        intent = getIntent();
        bddSa = new SaisonDAO(getApplicationContext());
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.5));

        //recup extra
        TextView CoActuel = (TextView) findViewById(R.id.textView18);
        if (intent.hasExtra("dbPo")){
            CoActuel.setText(getIntent().getStringExtra("dbPo")+"-"+getIntent().getStringExtra("df"));
        }



        //Suppression Entreprise
        Button suppSa = (Button) findViewById(R.id.suppCo);
        suppSa.setOnClickListener(suppressionSa);


    }
    private View.OnClickListener suppressionSa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (intent != null){
                Saison sa = new Saison (getIntent().getIntExtra("idSa",0), getIntent().getStringExtra("dbSa"), getIntent().getStringExtra("dfSa"));
                bddSa.open();
                bddSa.delete(sa);
                bddSa.close();
            }


            Intent intent = new Intent (getApplicationContext(), ActivitySaison.class);
            startActivity(intent);
        }
    };
}
