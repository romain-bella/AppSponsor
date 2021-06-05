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

import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.R;

public class PosteAdapter extends ArrayAdapter <Poste> {

    public PosteAdapter(Context context, ArrayList<Poste> listePoste){
        super(context, 0, listePoste);
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
                    R.layout.poste_spinner, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.textView16);

        Poste currentItem = getItem(position);

        textViewName.setText(currentItem.getNom_poste());
        return convertView;
    }
}
