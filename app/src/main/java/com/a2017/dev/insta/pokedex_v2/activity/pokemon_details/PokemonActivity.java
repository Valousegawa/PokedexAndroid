package com.a2017.dev.insta.pokedex_v2.activity.pokemon_details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a2017.dev.insta.pokedex_v2.R;
import com.a2017.dev.insta.pokedex_v2.activity.BaseActivity;
import com.a2017.dev.insta.pokedex_v2.activity.pokemon_list.PokemonListActivity;
import com.a2017.dev.insta.pokedex_v2.dagger.pokemon_details.DaggerPokemonComponent;
import com.a2017.dev.insta.pokedex_v2.dagger.pokemon_details.PokemonCleanModule;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_details.PokemonPresenter;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonViewModel;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Telest on 16/11/2017.
 * Basic activity to show one Pokemon
 */

public class PokemonActivity extends BaseActivity implements PokemonActivityView {

    @Inject
    PokemonPresenter presenter;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.weight) TextView weight;
    @BindView(R.id.height) TextView height;
    @BindView(R.id.sprite)
    ImageView sprite;
    @BindView(R.id.pb)
    ImageView pb;
    @BindView(R.id.infos)
    LinearLayout infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPokemonComponent.builder()
                .build()
                .plus(new PokemonCleanModule(this))
                .inject(this);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("position");
        presenter.load(id + 1);

        setContentView(R.layout.pokemon_details);
        ButterKnife.bind(this);

        Glide.with(getApplicationContext())
                .load(R.drawable.pika)
                .into(pb);
    }

    @Override
    public void displayPokemonName(PokemonViewModel viewModel) {
        pb.setVisibility(View.GONE);
        infos.setVisibility(View.VISIBLE);

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        name.setText(viewModel.getName().substring(0,1).toUpperCase() + viewModel.getName().substring(1));
        height.setText(df.format(viewModel.getHeight() * 0.1) + " m");
        weight.setText(df.format(viewModel.getWeight() * 0.1) + " kg");

        Picasso.with(PokemonActivity.this)
                .load(viewModel.getSprite().getFront_default())
                .resize(500,500)
                .centerCrop()
                .into(sprite);

    }

    @Override
    public void displayNotFound() {
        Toast.makeText(getApplicationContext(), "404 Team Rocket was here", Toast.LENGTH_LONG).show();
        this.onBackPressed();
    }
}