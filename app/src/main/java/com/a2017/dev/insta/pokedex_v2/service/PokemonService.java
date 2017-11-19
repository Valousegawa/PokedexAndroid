package com.a2017.dev.insta.pokedex_v2.service;

import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;
import com.a2017.dev.insta.pokedex_v2.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Telest on 16/11/2017.
 * Specify all the services available in the API
 * Cheers up dear, cavalry's here !
 */

public interface PokemonService {
    @Headers({"Accept: application/json"})
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") int id);
    @GET("pokemon/?limit=20")
    Call<ListPokemon> getAllPokemons();
    @GET("pokemon/{sch}")
    Call<Pokemon> getPokemon(@Path("sch") String sch);
}
