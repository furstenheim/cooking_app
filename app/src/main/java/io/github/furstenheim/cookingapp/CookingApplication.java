package io.github.furstenheim.cookingapp;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class CookingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        storeInitialization();
    }

    private void storeInitialization() {
        AppStore appStore = AppStore.AppStore();
        if (isPersistenceEnabled()) {
            appStore.enablePersistence();
        }
    }

    private Boolean isPersistenceEnabled() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean(getString(R.string.pref_persistence_key), true);
    }
}
