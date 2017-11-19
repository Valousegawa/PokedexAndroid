package com.a2017.dev.insta.pokedex_v2.activity.pokemon_search;

import android.app.SearchManager;
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
import com.a2017.dev.insta.pokedex_v2.dagger.pokemon_search.DaggerPokemonSearchComponent;
import com.a2017.dev.insta.pokedex_v2.dagger.pokemon_search.PokemonSearchCleanModule;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_search.PokemonSearchPresenter;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonSearchViewModel;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Telest on 16/11/2017.
 * Basic activity to show one Pokemon after search
 */

public class PokemonSearchActivity extends BaseActivity implements PokemonSearchActivityView {

    @Inject
    PokemonSearchPresenter presenter;

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
        DaggerPokemonSearchComponent.builder()
                .build()
                .plus(new PokemonSearchCleanModule(this))
                .inject(this);

        Intent intent = getIntent();
        String query = intent.getStringExtra(SearchManager.QUERY);
        presenter.load(query);

        setContentView(R.layout.pokemon_details);
        ButterKnife.bind(this);

        Glide.with(getApplicationContext())
                .load(R.drawable.pika)
                .into(pb);
    }

    @Override
    public void displayPokemon(PokemonSearchViewModel viewModel) {
        pb.setVisibility(View.GONE);
        infos.setVisibility(View.VISIBLE);

        name.setText(viewModel.getName().substring(0,1).toUpperCase() + viewModel.getName().substring(1));
        height.setText(String.format(String.valueOf(viewModel.getHeight() * 10), "%.1f") + " cm");
        weight.setText(String.format(String.valueOf(viewModel.getWeight() * 0.1), "%.1f") + " kg");

        Picasso.with(PokemonSearchActivity.this)
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