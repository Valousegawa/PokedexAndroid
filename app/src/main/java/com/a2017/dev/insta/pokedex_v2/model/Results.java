package com.a2017.dev.insta.pokedex_v2.model;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Telest on 17/11/2017.
 * Show all the results after API query for all Pokemon
 */

public class Results {
    private String name;
    private String url;

    public Results(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String retrieveId(){
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] segments = uri.getPath().split("/");
        return segments[segments.length-1];
    }
}
