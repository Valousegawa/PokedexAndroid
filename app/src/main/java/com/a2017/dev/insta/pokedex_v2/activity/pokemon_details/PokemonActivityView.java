package com.a2017.dev.insta.pokedex_v2.activity.pokemon_details;

import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonViewModel;

/**
 * Created by Telest on 16/11/2017.
 */

public interface PokemonActivityView {
    void displayPokemonName(final PokemonViewModel viewModel);
    void displayNotFound();
}
