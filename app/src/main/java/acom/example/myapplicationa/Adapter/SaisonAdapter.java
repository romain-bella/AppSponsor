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
import acom.example.myapplicationa.Metier.Saison;
import acom.example.myapplicationa.R;

public class SaisonAdapter extends ArrayAdapter<Saison> {
    public SaisonAdapter(Context context, ArrayList<Saison> listeS){
        super(context, 0, listeS);
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
                    R.layout.saison_spinner, parent, false
            );
        }
        TextView dd = convertView.findViewById(R.id.textView24);
        TextView df = convertView.findViewById(R.id.textView25);

        Saison currentItem = getItem(position);

        dd.setText(currentItem.getDateDebut());
        df.setText(currentItem.getDateFin());
        return convertView;
    }
}
