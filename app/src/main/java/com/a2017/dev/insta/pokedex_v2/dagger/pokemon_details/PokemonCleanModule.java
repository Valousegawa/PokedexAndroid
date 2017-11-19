package com.a2017.dev.insta.pokedex_v2.dagger.pokemon_details;

import com.a2017.dev.insta.pokedex_v2.activity.pokemon_details.PokemonActivityView;
import com.a2017.dev.insta.pokedex_v2.dagger.PokemonModule;
import com.a2017.dev.insta.pokedex_v2.interactor.PokemonInteractor;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_details.PokemonPresenter;
import com.a2017.dev.insta.pokedex_v2.presenter.pokemon_details.PokemonPresenterImpl;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepository;
import com.a2017.dev.insta.pokedex_v2.repository.PokemonRepositoryImpl;
import com.a2017.dev.insta.pokedex_v2.viewmodel.PokemonViewModel;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Telest on 16/11/2017.
 */

@Module
public class PokemonCleanModule {

    private final PokemonActivityView view;

    public PokemonCleanModule(PokemonActivityView view) {
        this.view = view;
    }

    @Provides
    PokemonPresenter providePokemonPresenter(
        @Named(PokemonModule.BACKGROUND_THREAD) Executor executor,
                PokemonActivityView view,
                        PokemonInteractor interactor
    ) {
        return new PokemonPresenterDecorator(executor, new PokemonPresenterImpl(view, interactor));
    }

    @Provides
    PokemonActivityView providePokemonActivityView(
            @Named(PokemonModule.MAIN_THREAD) Executor executor
    ){
        return new PokemonCleanViewDecorator(executor, view);
    }

    @Provides
    PokemonInteractor providePokemonInteractor(PokemonRepository repository){
        return new PokemonInteractor(repository);
    }

    @Provides
    PokemonRepository providePokemonRepository(){
        return new PokemonRepositoryImpl();
    }


    class PokemonPresenterDecorator implements PokemonPresenter {

        private Executor executor;
        private PokemonPresenter presenter;

        public PokemonPresenterDecorator(Executor executor, PokemonPresenter presenter) {
            this.executor = executor;
            this.presenter = presenter;
        }

        @Override
        public void load(final int id) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    presenter.load(id);
                }
            });
        }
    }

    class PokemonCleanViewDecorator implements PokemonActivityView {
        private Executor executor;
        private PokemonActivityView view;

        public PokemonCleanViewDecorator(Executor executor, PokemonActivityView view) {
            this.executor = executor;
            this.view = view;
        }

        @Override
        public void displayPokemonName(final PokemonViewModel viewModel) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    view.displayPokemonName(viewModel);
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
