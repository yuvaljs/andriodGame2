package aaaa.dfs;

import android.app.Application;

import aaaa.dfs.utilities.SharedPreferencesManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(this);
    }

}
