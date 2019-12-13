package com.example.criminalintent.model;

public class CrimeStoreProvider {

    private static CrimeStore instance;

    private CrimeStoreProvider() {
    }

    public static CrimeStore getInstance() {
        if (instance == null) {
            instance = new InMemoryCrimeStore();
        }
        return instance;
    }

}
