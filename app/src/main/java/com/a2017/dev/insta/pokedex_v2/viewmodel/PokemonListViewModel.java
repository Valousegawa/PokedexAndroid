package com.a2017.dev.insta.pokedex_v2.viewmodel;

import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;

/**
 * Created by Telest on 17/11/2017.
 * Here to show ALL DA POKEMONS ! 805 Carl, 805 !!
 * (but only the name and the sprite tho, is it enough ... ?)
 */

public class PokemonListViewModel {
    private final ListPokemon pokemons;

    public PokemonListViewModel(ListPokemon pokemons) {
        this.pokemons = pokemons;
    }

    public ListPokemon getPokemons() {
        return pokemons;
    }
}
