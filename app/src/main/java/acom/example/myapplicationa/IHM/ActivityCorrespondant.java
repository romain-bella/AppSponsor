package acom.example.myapplicationa.IHM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.CorrespondantDAO;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.R;

public class ActivityCorrespondant extends MainActivity {

    private ListView listeCorrespondant;
    private CorrespondantDAO bddCo;
    private Button boutonRetour, boutonAjout;
    private EditText searchFilterCo;
    private ArrayAdapter<Correspondant> listCorrespondantAdaptater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correspondant);

        //Bouton Retour
        boutonRetour = (Button) findViewById(R.id.buttonR);
        boutonRetour.setOnClickListener(RetourListener);


        //Affichage list des Correspondant
        listeCorrespondant = (ListView) findViewById(R.id.listCorrespondant);
        bddCo = new CorrespondantDAO (this);
        bddCo.open();
        ArrayList<Correspondant> values = new ArrayList<Correspondant>();
        values = bddCo.read();
        listCorrespondantAdaptater = new ArrayAdapter<Correspondant>(this, android.R.layout.simple_list_item_1, values);
        listeCorrespondant.setAdapter(listCorrespondantAdaptater);

        /*for (int i=0;i<=bddCo.compte();i++){
            values = values+bddCo.read(i);
        }*/

        bddCo.close();
        //PopUp Suppression
        listeCorrespondant.setOnItemClickListener(suppressionCo);

        //Creation d'un nouveau correspondant
        boutonAjout = (Button) findViewById(R.id.boutonAjout);
        boutonAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCorrespondant.this,PopupAjoutCo.class));
            }
        });

        //filtre
        searchFilterCo = (EditText) findViewById(R.id.searchFilterCo);
        searchFilterCo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ActivityCorrespondant.this).listCorrespondantAdaptater.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private AdapterView.OnItemClickListener suppressionCo = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ActivityCorrespondant.this, PopupSuppCo.class);
            intent.putExtra("idCo", listCorrespondantAdaptater.getItem(position).getId_correspondant());
            intent.putExtra ("nomCo", listCorrespondantAdaptater.getItem(position).getNom_co());
            intent.putExtra ("telCo", listCorrespondantAdaptater.getItem(position).getTelephone());
            intent.putExtra ("mailCo", listCorrespondantAdaptater.getItem(position).getMail());
            intent.putExtra ("prenomCo", listCorrespondantAdaptater.getItem(position).getPrenom_co());
            intent.putExtra ("posteid",listCorrespondantAdaptater.getItem(position).getUnPoste().getId_poste());
            intent.putExtra ("postenom",listCorrespondantAdaptater.getItem(position).getUnPoste().getNom_poste());
            intent.putExtra("entid", listCorrespondantAdaptater.getItem(position).getUneEntreprise().getId_E());
            intent.putExtra("entnom",listCorrespondantAdaptater.getItem(position).getUneEntreprise().getRaison_sociale());
            intent.putExtra("entville",listCorrespondantAdaptater.getItem(position).getUneEntreprise().getVille());
            intent.putExtra("entrue",listCorrespondantAdaptater.getItem(position).getUneEntreprise().getRue());
            intent.putExtra("entCp",listCorrespondantAdaptater.getItem(position).getUneEntreprise().getCP());
            intent.putExtra("enttel",listCorrespondantAdaptater.getItem(position).getUneEntreprise().getTelephone());
            intent.putExtra("entmail",listCorrespondantAdaptater.getItem(position).getUneEntreprise().getMail());
            startActivity(intent);
        }
    };

    private View.OnClickListener RetourListener = v -> {
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    };


}