package com.a2017.dev.insta.pokedex_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a2017.dev.insta.pokedex_v2.R;
import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Telest on 17/11/2017.
 * Adapter to inflate Pokemon List display
 */

public class PokemonAdapter extends BaseAdapter {

    @BindView(R.id.name) TextView name;
    @BindView(R.id.sprite) ImageView sprite;

    private Context mContext;
    private LayoutInflater mInflater;
    private ListPokemon mDataSource;

    public PokemonAdapter(Context mContext, ListPokemon mDataSource) {
        this.mContext = mContext;
        this.mDataSource = mDataSource;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.pokemon_list_button_activity, parent, false);
        ButterKnife.bind(this,rowView);
        String upperString = mDataSource.getResults().get(position).getName().substring(0,1).toUpperCase() + mDataSource.getResults().get(position).getName().substring(1);
        name.setText(upperString);
        Glide.with(mContext)
                .load(mDataSource.getSpriteURL(position + 1))
                .into(sprite);
        return rowView;
    }
}
