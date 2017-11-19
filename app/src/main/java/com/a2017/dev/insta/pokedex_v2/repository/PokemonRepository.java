package com.a2017.dev.insta.pokedex_v2.repository;

import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonNotFoundException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonRepositoryException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_list.PokemonListRepositoryException;
import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;
import com.a2017.dev.insta.pokedex_v2.model.Pokemon;

/**
 * Created by Telest on 16/11/2017.
 */

public interface PokemonRepository {
    Pokemon getPokemonById(int id) throws PokemonRepositoryException, PokemonNotFoundException;
    ListPokemon getAllPokemon() throws PokemonListRepositoryException;
    Pokemon getPokemon(String sch) throws PokemonRepositoryException, PokemonNotFoundException;
}
