package com.suonk.musiciansocialnetwork.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.suonk.musiciansocialnetwork.R;
import com.suonk.musiciansocialnetwork.model.Musicians;

import java.util.List;

/**
 * La Classe qui permet de remplir la gridview avec les bon éléments
 *
 * @author Kenzy Suon
 */
public class MusiciansGridViewAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Musicians> listOfMusicians;

    public MusiciansGridViewAdapter(Context context, List<Musicians> listOfMusicians) {
        this.context = context;
        this.listOfMusicians = listOfMusicians;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listOfMusicians.size();
    }

    @Override
    public Musicians getItem(int position) {
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

        holder.musicians_grid_item_RoundedImage.setImageResource(listOfMusicians.get(position).getProfileImage());
        holder.musicians_grid_item_FirstName.setText(listOfMusicians.get(position).getName());

        gridview.setTag(holder);

        return gridview;
    }

    static class ViewHolder {
        TextView musicians_grid_item_FirstName;
        CircularImageView musicians_grid_item_RoundedImage;
    }
}
