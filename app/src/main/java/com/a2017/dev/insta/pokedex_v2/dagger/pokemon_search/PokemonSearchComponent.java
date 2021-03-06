package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_search;

import com.a2017.dev.insta.pokedex_v2.dagger.PokemonModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Telest on 16/11/2017.
 */

@Singleton
@Component(modules = PokemonModule.class)
public interface PokemonSearchComponent {
    PokemonSearchSubComponent plus(PokemonSearchCleanModule module);
}
