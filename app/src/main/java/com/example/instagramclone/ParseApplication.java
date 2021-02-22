package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bFuKDPjA72ERk7gYBMZEaD4FAyic5SSNbNVoiczc")
                .clientKey("6LIEYGBjV4VoFWJQfVJ8kyr5y0Ve8oPjZwVKt7Sf")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}


