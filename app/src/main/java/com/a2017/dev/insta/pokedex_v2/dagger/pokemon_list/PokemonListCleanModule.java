package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_list;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_list.PokemonListActivityView;
import com.a2017.dev.insta.pokedex_v2.dagger.PokemonModule;
import com.a2017.dev.insta.pokedex_v2.interactor.PokemonInteractor;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_list.PokemonListPresenter;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_list.PokemonListPresenterImpl;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepository;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepositoryImpl;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonListViewModel;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Telest on 16/11/2017.
 */

@Module
public class PokemonListCleanModule {

    private final PokemonListActivityView view;

    public PokemonListCleanModule(PokemonListActivityView view) {
        this.view = view;
    }

    @Provides
    PokemonListPresenter providePokemonListPresenter(
        @Named(PokemonModule.BACKGROUND_THREAD) Executor executor,
                PokemonListActivityView view,
                        PokemonInteractor interactor
    ) {
        return new PokemonListPresenterDecorator(executor, new PokemonListPresenterImpl(view, interactor));
    }

    @Provides
    PokemonListActivityView providePokemonListActivityView(
            @Named(PokemonModule.MAIN_THREAD) Executor executor
    ){
        return new PokemonListCleanViewDecorator(executor, view);
    }

    @Provides
    PokemonInteractor providePokemonListInteractor(PokemonRepository repository){
        return new PokemonInteractor(repository);
    }

    @Provides
    PokemonRepository providePokemoListRepository(){
        return new PokemonRepositoryImpl();
    }


    class PokemonListPresenterDecorator implements PokemonListPresenter {

        private Executor executor;
        private PokemonListPresenter presenter;

        public PokemonListPresenterDecorator(Executor executor, PokemonListPresenter presenter) {
            this.executor = executor;
            this.presenter = presenter;
        }

        @Override
        public void load() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    presenter.load();
                }
            });
        }
    }

    class PokemonListCleanViewDecorator implements PokemonListActivityView {
        private Executor executor;
        private PokemonListActivityView view;

        public PokemonListCleanViewDecorator(Executor executor, PokemonListActivityView view) {
            this.executor = executor;
            this.view = view;
        }

        @Override
        public void displayAllPokemon(final PokemonListViewModel viewModel) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayAllPokemon(viewModel);
                }
            });
        }

        @Override
        public void errorList() {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.errorList();
                }
            });
        }
    }
}
