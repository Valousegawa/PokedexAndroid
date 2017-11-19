package com.a2017.dev.insta.pokedex_v2.activity.pokemon_search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Telest on 17/11/2017.
 * Search a Pokemon by id or name
 */

public class SearchResultsActivityHud extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Intent i = new Intent(this, PokemonSearchActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("position", query);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
