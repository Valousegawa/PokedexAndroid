package com.a2017.dev.insta.pokedex_v2.activity.pokemon_list;

import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonListViewModel;

/**
 * Created by Telest on 16/11/2017.
 */

public interface PokemonListActivityView {
    void displayAllPokemon(final PokemonListViewModel viewModel);
    void errorList();
}
