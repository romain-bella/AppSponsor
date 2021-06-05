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

import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.R;

public class EntrepriseAdapter extends ArrayAdapter<Entreprise> {

    public EntrepriseAdapter(Context context, ArrayList<Entreprise> listeEntreprise){
        super(context, 0, listeEntreprise);
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
                    R.layout.entreprise_spinner, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.textView17);

        Entreprise currentItem = getItem(position);

        textViewName.setText(currentItem.getRaison_sociale());
        return convertView;
    }
}


