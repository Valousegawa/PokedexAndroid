package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_list;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_list.PokemonListActivity;

import dagger.Subcomponent;

/**
 * Created by Telest on 16/11/2017.
 */

@Subcomponent(modules = PokemonListCleanModule.class)
public interface PokemonListSubComponent {
    void inject(PokemonListActivity activity);
}
