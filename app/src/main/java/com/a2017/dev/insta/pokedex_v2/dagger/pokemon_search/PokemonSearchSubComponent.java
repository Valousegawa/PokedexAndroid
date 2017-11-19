package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_search;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_search.PokemonSearchActivity;

import dagger.Subcomponent;

/**
 * Created by Telest on 16/11/2017.
 */

@Subcomponent(modules = PokemonSearchCleanModule.class)
public interface PokemonSearchSubComponent {
    void inject(PokemonSearchActivity activity);
}
