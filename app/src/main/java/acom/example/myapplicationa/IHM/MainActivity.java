package acom.example.myapplicationa.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import acom.example.myapplicationa.R;

public class MainActivity extends AppCompatActivity {

    private Button boutonC;
    private AlertDialog.Builder alertDialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bouton pour les correspondants
        boutonC = (Button) findViewById(R.id.buttonC);
        boutonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ActivityCorrespondant.class);
                startActivity(intent);
                finish();
            }
        });
        //bouton pour les Entreprises
        boutonC = (Button) findViewById(R.id.buttonE);
        boutonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ActivityEntreprise.class);
                startActivity(intent);
                finish();
            }
        });
        //bouton pour les Postes
        boutonC = (Button) findViewById(R.id.buttonP);
        boutonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ActivityPoste.class);
                startActivity(intent);
                finish();
            }
        });

        //bouton pour les Saisons
        boutonC = (Button) findViewById(R.id.buttonS);
        boutonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ActivitySaison.class);
                startActivity(intent);
                finish();
            }
        });

        //bouton pour les Donnations
        boutonC = (Button) findViewById(R.id.buttonD);
        boutonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ActivityDonner.class);
                startActivity(intent);
                finish();
            }
        });

        //bouton quitter
        alertDialogBuilder = new AlertDialog.Builder(this);
        boutonC = (Button) findViewById(R.id.button2);
        boutonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.setTitle("Quitter l'application")
                        .setMessage("voulez vous vraiment quitter l'application ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("Non",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
