package com.a2017.dev.insta.pokedex_v2.model;

import java.util.List;

/**
 * Created by Telest on 17/11/2017.
 * Class that represents the list of all Pokrmon retrieves by the API
 */

public class ListPokemon {
    private String count;
    private String previous;
    private List<Results> results;
    private String next;

    public String getCount() {
        return count;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Results> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }

    public int size(){
        return results.size();
    }

    public Results get(int id){
        return results.get(id);
    }

    public String getSpriteURL(int id){
        return "http://pokeapi.co/media/sprites/pokemon/" + id + ".png";
    }
}
