package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import acom.example.myapplicationa.DAO.EntrepriseDAO;
import acom.example.myapplicationa.DAO.PosteDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.R;

public class PopupSuppPo extends ActivityPoste{
    private PosteDAO bddPo;
    private Intent intent;
    private int idPo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsupppo);
        intent = getIntent();
        bddPo = new PosteDAO(getApplicationContext());
        //PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.5),(int)(height*.5));

        //recup extra
        TextView CoActuel = (TextView) findViewById(R.id.textView18);
        if (intent.hasExtra("nomPo")){
            CoActuel.setText(getIntent().getStringExtra("nomPo"));
        }



        //Suppression Entreprise
        Button suppPo = (Button) findViewById(R.id.suppCo);
        suppPo.setOnClickListener(suppressionPo);

        //modif Poste

        TextView textView13 = (TextView) findViewById(R.id.textView13);
        textView13.setText("Ancien nom: "+getIntent().getStringExtra("nomPo"));

        Button suppCo2 = (Button) findViewById(R.id.suppCo2);

        EditText editText2 = (EditText) findViewById(R.id.editText2);
        suppCo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bddPo.open();
                Poste Po = new Poste(getIntent().getIntExtra("idPo", 0),editText2.getText().toString());
                bddPo.update(Po);
                bddPo.close();
                Intent intent = new Intent (getApplicationContext(), ActivityPoste.class);
                startActivity(intent);
            }
        });

    }
    private View.OnClickListener suppressionPo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (intent != null){
                Poste unPo = new Poste (getIntent().getIntExtra("idPo",0), getIntent().getStringExtra("nomPo"));
                bddPo.open();
                bddPo.delete(unPo);
                bddPo.close();
            }


            Intent intent = new Intent (getApplicationContext(), ActivityPoste.class);
            startActivity(intent);
        }
    };
}
