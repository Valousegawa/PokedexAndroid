package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_details;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_details.PokemonActivity;

import dagger.Subcomponent;

/**
 * Created by Telest on 16/11/2017.
 */

@Subcomponent(modules = PokemonCleanModule.class)
public interface PokemonSubComponent {
    void inject(PokemonActivity activity);
}
