package com.a2017.dev.insta.pokedex_v2.presenter.pokemon_details;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_details.PokemonActivityView;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonNotFoundException;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_details.PokemonRepositoryException;
import com.a2017.dev.insta.pokedex_v2.interactor.PokemonInteractor;
import com.a2017.dev.insta.pokedex_v2.model.Pokemon;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonViewModel;

/**
 * Created by Telest on 16/11/2017.
 */

public class PokemonPresenterImpl implements PokemonPresenter {

    private final PokemonActivityView view;
    private final PokemonInteractor interactor;

    public PokemonPresenterImpl(PokemonActivityView view, PokemonInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void load(int id) {
        try {
            Pokemon pokemon = interactor.getPokemonById(id);
            PokemonViewModel viewModel = new PokemonViewModel(pokemon.getName(), pokemon.getHeight(), pokemon.getWeight(), pokemon.getPokemonSprite());
            view.displayPokemonName(viewModel);
        }catch (PokemonNotFoundException e) {
            view.displayNotFound();
        }catch (PokemonRepositoryException e){
            view.displayNotFound();
        }
    }
}
