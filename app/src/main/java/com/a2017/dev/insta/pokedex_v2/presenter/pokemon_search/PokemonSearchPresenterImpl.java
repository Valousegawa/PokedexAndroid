package com.a2017.dev.insta.pokedex_v2.presenter.pokemon_search;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_search.PokemonSearchActivityView;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonNotFoundException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonRepositoryException;
import com.a2017.dev.insta.pokedex_v2.interactor.PokemonInteractor;
import com.a2017.dev.insta.pokedex_v2.model.Pokemon;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonSearchViewModel;

/**
 * Created by Telest on 17/11/2017.
 */

public class PokemonSearchPresenterImpl implements PokemonSearchPresenter {
    private final PokemonSearchActivityView view;
    private final PokemonInteractor interactor;

    public PokemonSearchPresenterImpl(PokemonSearchActivityView view, PokemonInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void load(String sch) {
        try {
            Pokemon pokemon = interactor.getPokemon(sch);
            PokemonSearchViewModel viewModel = new PokemonSearchViewModel(pokemon.getName(), pokemon.getHeight(), pokemon.getWeight(), pokemon.getPokemonSprite());
            view.displayPokemon(viewModel);
        }catch (PokemonNotFoundException e) {
            view.displayNotFound();
        }catch (PokemonRepositoryException e){
            view.displayNotFound();
        }
    }
}
