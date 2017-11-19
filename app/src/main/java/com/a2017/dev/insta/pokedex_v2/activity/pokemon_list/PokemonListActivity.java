package com.a2017.dev.insta.pokedex_v2.activity.pokemon_list;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.a2017.dev.insta.pokedex_v2.R;
import com.a2017.dev.insta.pokedex_v2.activity.BaseActivity;
import com.a2017.dev.insta.pokedex_v2.activity.pokemon_details.PokemonActivity;
import com.a2017.dev.insta.pokedex_v2.activity.pokemon_search.PokemonSearchActivity;
import com.a2017.dev.insta.pokedex_v2.adapter.PokemonAdapter;
import com.a2017.dev.insta.pokedex_v2.dagger.pokemon_list.DaggerPokemonListComponent;
import com.a2017.dev.insta.pokedex_v2.dagger.pokemon_list.PokemonListCleanModule;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_list.PokemonListPresenter;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonListViewModel;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Telest on 16/11/2017.
 * Basic activity to show all the Pokemon
 */

public class PokemonListActivity extends BaseActivity implements PokemonListActivityView {

    @Inject
    PokemonListPresenter presenter;

    @BindView(R.id.list_pokemon)
    ListView list;
    @BindView(R.id.pb)
    ImageView pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        DaggerPokemonListComponent.builder()
                .build()
                .plus(new PokemonListCleanModule(this))
                .inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.load();

        Glide.with(getApplicationContext())
                .load(R.drawable.pika)
                .into(pb);
    }

    @Override
    public void displayAllPokemon(PokemonListViewModel viewModel) {
        pb.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);

        PokemonAdapter adapter = new PokemonAdapter(this, viewModel.getPokemons());
        list.setAdapter(adapter);
    }

    @Override
    public void errorList() {
        Toast.makeText(getApplicationContext(), "Team Rocket was here", Toast.LENGTH_LONG).show();
    }

    @OnItemClick(R.id.list_pokemon)
    public void onPokemonClick(int position) {
        Intent i = new Intent(getApplicationContext(), PokemonActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        /*searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(new ComponentName(this, PokemonSearchActivity.class)));

        return true;
    }
}