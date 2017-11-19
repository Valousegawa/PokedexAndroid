package com.a2017.dev.insta.pokedex_v2.presenter.pokemon_list;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_list.PokemonListActivityView;
import com.a2017.dev.insta.pokedex_v2.exceptions.pokemon_list.PokemonListRepositoryException;
import com.a2017.dev.insta.pokedex_v2.interactor.PokemonInteractor;
import com.a2017.dev.insta.pokedex_v2.model.ListPokemon;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonListViewModel;

/**
 * Created by Telest on 16/11/2017.
 */

public class PokemonListPresenterImpl implements PokemonListPresenter {

    private final PokemonListActivityView view;
    private final PokemonInteractor interactor;

    public PokemonListPresenterImpl(PokemonListActivityView view, PokemonInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void load() {
        try {
            ListPokemon pokemon = interactor.getAllPokemon();
            PokemonListViewModel viewModel = new PokemonListViewModel(pokemon);
            view.displayAllPokemon(viewModel);
        }catch (PokemonListRepositoryException e){
            view.errorList();
        }
    }
}
