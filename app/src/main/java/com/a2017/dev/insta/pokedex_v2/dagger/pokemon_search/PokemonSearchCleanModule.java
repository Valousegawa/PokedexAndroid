package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_search;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_search.PokemonSearchActivityView;
import com.a2017.dev.insta.pokedex_v2.dagger.PokemonModule;
import com.a2017.dev.insta.pokedex_v2.interactor.PokemonInteractor;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_search.PokemonSearchPresenter;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_search.PokemonSearchPresenterImpl;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepository;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepositoryImpl;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonSearchViewModel;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Telest on 16/11/2017.
 */

@Module
public class PokemonSearchCleanModule {

    private final PokemonSearchActivityView view;

    public PokemonSearchCleanModule(PokemonSearchActivityView view) {
        this.view = view;
    }

    @Provides
    PokemonSearchPresenter providePokemonSearchPresenter(
        @Named(PokemonModule.BACKGROUND_THREAD) Executor executor,
                PokemonSearchActivityView view,
                        PokemonInteractor interactor
    ) {
        return new PokemonSearchPresenterDecorator(executor, new PokemonSearchPresenterImpl(view, interactor));
    }

    @Provides
    PokemonSearchActivityView providePokemonSearchActivityView(
            @Named(PokemonModule.MAIN_THREAD) Executor executor
    ){
        return new PokemonSearchCleanViewDecorator(executor, view);
    }

    @Provides
    PokemonInteractor providePokemonSearchInteractor(PokemonRepository repository){
        return new PokemonInteractor(repository);
    }

    @Provides
    PokemonRepository providePokemonSearchRepository(){
        return new PokemonRepositoryImpl();
    }


    class PokemonSearchPresenterDecorator implements PokemonSearchPresenter {

        private Executor executor;
        private PokemonSearchPresenter presenter;

        public PokemonSearchPresenterDecorator(Executor executor, PokemonSearchPresenter presenter) {
            this.executor = executor;
            this.presenter = presenter;
        }

        @Override
        public void load(final String sch) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    presenter.load(sch);
                }
            });
        }
    }

    class PokemonSearchCleanViewDecorator implements PokemonSearchActivityView {
        private Executor executor;
        private PokemonSearchActivityView view;

        public PokemonSearchCleanViewDecorator(Executor executor, PokemonSearchActivityView view) {
            this.executor = executor;
            this.view = view;
        }

        @Override
        public void displayPokemon(final PokemonSearchViewModel viewModel) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayPokemon(viewModel);
                }
            });
        }

        @Override
        public void displayNotFound() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayNotFound();
                }
            });
        }
    }
}
