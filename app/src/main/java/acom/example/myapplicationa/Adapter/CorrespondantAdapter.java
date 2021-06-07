package acom.example.myapplicationa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.R;

public class CorrespondantAdapter extends ArrayAdapter<Correspondant> {
    public CorrespondantAdapter(Context context, ArrayList<Correspondant> listeC){
        super(context, 0, listeC);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.correspondant_spinner, parent, false
            );
        }
        TextView nomE = convertView.findViewById(R.id.textView24);
        TextView nomC = convertView.findViewById(R.id.textView25);
        TextView prenomC = convertView.findViewById(R.id.textView17);

        Correspondant currentItem = getItem(position);

        nomE.setText(currentItem.getUneEntreprise().getRaison_sociale());
        nomC.setText(currentItem.getNom_co());
        prenomC.setText(currentItem.getPrenom_co());
        return convertView;
    }
}
