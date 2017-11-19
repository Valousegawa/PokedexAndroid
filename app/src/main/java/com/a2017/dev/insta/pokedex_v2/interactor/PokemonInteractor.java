package com.a2017.dev.insta.pokedex_v2.interactor;

import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonNotFoundException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonRepositoryException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_list.PokemonListRepositoryException;
import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;
import com.a2017.dev.insta.pokedex_v2.model.Pokemon;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepository;

/**
 * Created by Telest on 16/11/2017.
 * Interactor that communicate with the repository
 */

public class PokemonInteractor {
    private final PokemonRepository repository;

    public PokemonInteractor(PokemonRepository repository) {
        this.repository = repository;
    }

    public Pokemon getPokemonById(int id)throws PokemonNotFoundException, PokemonRepositoryException {
        return repository.getPokemonById(id);
    }

    public ListPokemon getAllPokemon() throws PokemonListRepositoryException{
        return repository.getAllPokemon();
    }

    public Pokemon getPokemon(String sch) throws PokemonNotFoundException, PokemonRepositoryException{
        return repository.getPokemon(sch);
    }
}
