package com.example.criminalintent.model;

import android.content.Context;

public class CrimeStoreProvider {

    private static CrimeStore instance;

    private CrimeStoreProvider() {
    }

    public static CrimeStore getInstance(Context context) {
        if (instance == null) {
//            instance = new InMemoryCrimeStore();
            instance = new SharePreferencesCrimeStore(context);
        }
        return instance;
    }

}
