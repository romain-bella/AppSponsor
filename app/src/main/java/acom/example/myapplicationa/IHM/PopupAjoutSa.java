package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.DAO.SaisonDAO;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class PopupAjoutSa extends ActivitySaison{
    private Button boutonAjoutSa;
    private EditText editText0, editText1;
    private SaisonDAO bddSa;
    private Saison uneSa;
    private ArrayList<Saison> listSa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupajoutsa);
        bddSa = new SaisonDAO(getApplicationContext());

        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.3));

        //Ajout
        editText0 = (EditText) findViewById(R.id.searchFilterCo);
        editText1 = (EditText) findViewById(R.id.editText1);
        boutonAjoutSa = (Button) findViewById(R.id.nouveauCo);
        boutonAjoutSa.setOnClickListener(ajoutSaListener);

    }

    private View.OnClickListener ajoutSaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String dd = editText0.getText().toString();
            String df = editText1.getText().toString();
            bddSa.open();
            Saison laSa = new Saison(bddSa.last()+1,dd,df);
            bddSa.insert(laSa);
            bddSa.close();
            Intent intent = new Intent (getApplicationContext(), ActivitySaison.class);
            startActivity(intent);
        }
    };
}
