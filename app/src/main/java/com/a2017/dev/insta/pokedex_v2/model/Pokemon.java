package com.a2017.dev.insta.pokedex_v2.model;

/**
 * Created by Telest on 16/11/2017.
 * Generic class that can save a basic Poekmon
 */

public class Pokemon {
    private String name;
    private  int weight;
    private int height;
    private PokemonSprite sprites;

    public PokemonSprite getPokemonSprite() {
        return sprites;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
