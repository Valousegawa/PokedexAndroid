package com.a2017.dev.insta.pokedex_v2.repository;

import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonNotFoundException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonRepositoryException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_list.PokemonListRepositoryException;
import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;
import com.a2017.dev.insta.pokedex_v2.model.Pokemon;
import com.a2017.dev.insta.pokedex_v2.service.PokemonService;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Telest on 16/11/2017.
 * This is the repo, where all methods will be displayed
 */

public class PokemonRepositoryImpl implements PokemonRepository {

    //Get one Pokemon
    @Override
    public Pokemon getPokemonById(int id) throws PokemonRepositoryException, PokemonNotFoundException {
        PokemonService service = getPokemonService();

        try {
            Pokemon pokemon = service.getPokemonById(id).execute().body();
            if (pokemon == null){
                throw new PokemonNotFoundException();
            }
            return pokemon;
        } catch (IOException e){
            throw new PokemonRepositoryException();
        }
    }

    //Gotta catch 'em all !
    @Override
    public ListPokemon getAllPokemon() throws PokemonListRepositoryException {
        PokemonService service = getPokemonService();

        try {
            ListPokemon pokemons = service.getAllPokemons().execute().body();
            if (pokemons == null){
                throw new PokemonListRepositoryException();
            }
            return pokemons;
        } catch (IOException e) {
            throw new PokemonListRepositoryException();
        }
    }

    @Override
    public Pokemon getPokemon(String sch) throws PokemonRepositoryException, PokemonNotFoundException {
        PokemonService service = getPokemonService();

        try {
            Pokemon pokemon = service.getPokemon(sch).execute().body();
            if (pokemon == null){
                throw new PokemonNotFoundException();
            }
            return pokemon;
        } catch (IOException e){
            throw new PokemonRepositoryException();
        }
    }

    //Gonna optimize that, a little
    private PokemonService getPokemonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://pokeapi.co/api/v2/")
                .build();

        return retrofit.create(PokemonService.class);
    }
}
