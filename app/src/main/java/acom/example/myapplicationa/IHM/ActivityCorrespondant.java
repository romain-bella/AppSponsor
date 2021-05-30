package acom.example.myapplicationa.IHM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.R;

public class ActivityCorrespondant extends MainActivity {

    private ListView listeCorrespondant;
    private CorrespondantDAO bddCo;
    private Button boutonRetour, boutonAjout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correspondant);

        //Bouton Retour
        boutonRetour = (Button) findViewById(R.id.buttonR);
        boutonRetour.setOnClickListener(RetourListener);
        /*
        //Affichage list des Correspondant
        listeCorrespondant = (ListView) findViewById(R.id.listCorrespondant);
        bddCo = new CorrespondantDAO (this);
        bddCo.open();
        ArrayList<Correspondant> values = new ArrayList<Correspondant>();
        values = bddCo.read();
        Log.v("TEST",values.toString());
        ArrayAdapter<Correspondant> listCorrespondantAdaptater = new ArrayAdapter<Correspondant>(this, android.R.layout.simple_list_item_1, values);
        listeCorrespondant.setAdapter(listCorrespondantAdaptater);
        bddCo.close();*/

        //Creation d'un nouveau correspondant
        boutonAjout = (Button) findViewById(R.id.boutonAjout);
        boutonAjout.setOnClickListener(popupAjout);

    }

    private View.OnClickListener RetourListener = v -> {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    };

    private View.OnClickListener popupAjout = v -> {
        startActivity(new Intent (MainActivity.this,Pop.class));
    }
}