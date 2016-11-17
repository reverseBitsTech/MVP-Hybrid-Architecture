package com.reversebits.projects.app.hybridmvp;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class Application extends android.app.Application {

    private static Context context;
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "1k1HsS9TLJF4ATWesSm5fdnKe";
    private static final String TWITTER_SECRET = "0C1rGB7zvAxD63GuIvNwTBb1a0bQktJHF66qwyizPdkFduXoXd";

    public void onCreate() {
        super.onCreate();
        Application.context = getApplicationContext();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(context);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext() {
        return Application.context;
    }
}
