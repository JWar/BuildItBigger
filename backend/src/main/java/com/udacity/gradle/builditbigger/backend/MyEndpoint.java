package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.portfolio.udacity.android.javajokes.Jokes;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** Gets joke from Jokes */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        Jokes joke = new Jokes();
        MyBean response = new MyBean();
        response.setData(joke.getJoke());
        return response;
    }

}
