package kz.growit.altynorda.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kz.growit.altynorda.Models.BooleanOption;
import kz.growit.altynorda.R;

/**
 * Created by Талгат on 28.11.2015.
 */
public class BooleansAdapter extends BaseAdapter {
    private ArrayList<BooleanOption> booleanOptions;

    public BooleansAdapter(ArrayList<BooleanOption> booleanOptions) {
        this.booleanOptions = booleanOptions;
    }

    @Override
    public int getCount() {
        return booleanOptions.size();
    }

    @Override
    public Object getItem(int position) {
        return booleanOptions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.boolean_option_row, parent, false);

        TextView key, value;
        ImageView checkedIV;
        key = (TextView) convertView.findViewById(R.id.keyBooleanOptionTV);
        value = (TextView) convertView.findViewById(R.id.valueBooleanOptionTV);
        checkedIV = (ImageView) convertView.findViewById(R.id.ischeckedBooleanOptionIV);

        key.setText(booleanOptions.get(position).getKey());

        if (booleanOptions.get(position).isValue())
            value.setText("да");
        else {
            checkedIV.setVisibility(View.INVISIBLE);
            value.setText("нет");
        }

        return convertView;
    }
}
