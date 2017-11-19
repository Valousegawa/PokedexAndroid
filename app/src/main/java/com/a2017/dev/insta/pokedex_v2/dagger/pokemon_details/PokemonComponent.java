package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_details;

import com.a2017.dev.insta.pokedex_v2.dagger.PokemonModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Telest on 16/11/2017.
 */

@Singleton
@Component(modules = PokemonModule.class)
public interface PokemonComponent {
    PokemonSubComponent plus(PokemonCleanModule module);
}
