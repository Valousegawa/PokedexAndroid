package com.a2017.dev.insta.pokedex_v2.activity.pokemon_search;

import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonSearchViewModel;
/**
 * Created by Telest on 17/11/2017.
 */

public interface PokemonSearchActivityView {
    void displayPokemon(final PokemonSearchViewModel viewModel);
    void displayNotFound();
}
