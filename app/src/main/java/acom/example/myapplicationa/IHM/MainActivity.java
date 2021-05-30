package acom.example.myapplicationa.IHM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import acom.example.myapplicationa.R;

public class MainActivity extends AppCompatActivity {

    private Button boutonC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //bouton pour l'entreprise mais qui affiche les correspondants
        boutonC = (Button) findViewById(R.id.buttonE);
        boutonC.setOnClickListener(CorrespondantListener);

    }

    private View.OnClickListener CorrespondantListener = new View.OnClickListener() {
        public void onClick(View v){
            Intent intent = new Intent (getApplicationContext(), ActivityCorrespondant.class);
            startActivity(intent);
            finish();
        }
    };
}
