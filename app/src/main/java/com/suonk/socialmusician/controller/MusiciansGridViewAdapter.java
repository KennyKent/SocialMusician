package com.suonk.socialmusician.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suonk.socialmusician.R;
import com.suonk.socialmusician.model.MusicianDB;

import java.util.List;

/**
 * La Classe qui permet de remplir la gridview avec les bon éléments
 *
 * @author Kenzy Suon
 */
public class MusiciansGridViewAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<MusicianDB> listOfMusicians;

    public MusiciansGridViewAdapter(Context context, List<MusicianDB> listOfMusicians) {
        this.context = context;
        this.listOfMusicians = listOfMusicians;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listOfMusicians.size();
    }

    @Override
    public MusicianDB getItem(int position) {
        return listOfMusicians.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"InflateParams", "ResourceType", "ViewHolder"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View gridview = convertView;
        ViewHolder holder;

        gridview = layoutInflater.inflate(R.layout.grid_musicians_item_layout, null);

        holder = new ViewHolder();
        holder.musicians_grid_item_RoundedImage = gridview.findViewById(R.id.grid_musicians_item_rounded_image);
        holder.musicians_grid_item_FirstName = gridview.findViewById(R.id.grid_musicians_item_first_name);

//        holder.musicians_grid_item_RoundedImage.setImageResource(listOfMusicians.get(position).getProfilePicture());
//        holder.musicians_grid_item_FirstName.setText(listOfMusicians.get(position).getFirstName() + " " + listOfMusicians.get(position).getLastName());

        gridview.setTag(holder);

        return gridview;
    }

    static class ViewHolder {
        TextView musicians_grid_item_FirstName;
        CircularImageView musicians_grid_item_RoundedImage;
    }
}
