package com.a2017.dev.insta.pokedex_v2.viewmodel;

import com.a2017.dev.insta.pokedex_v2.model.PokemonSprite;

/**
 * Created by Telest on 16/11/2017.
 * A modelView for one Pokemon
 */

public class PokemonViewModel {
    private final String name;
    private final int height;
    private final int weight;
    private final PokemonSprite sprite;

    public PokemonViewModel(String name, int height, int weight, PokemonSprite sprite) {

        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public PokemonSprite getSprite() {
        return sprite;
    }
}
