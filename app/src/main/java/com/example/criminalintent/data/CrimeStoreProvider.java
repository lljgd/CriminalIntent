package com.example.criminalintent.data;

import android.content.Context;

import com.example.criminalintent.data.room.RoomCrimeStore;

public class CrimeStoreProvider {

    private static CrimeStore instance;

    private CrimeStoreProvider() {
    }

    public static CrimeStore getInstance(Context context) {
        if (instance == null) {
//            instance = new InMemoryCrimeStore();
//            instance = new SharedPreferencesCrimeStore2(context);
//            instance = new SharedPreferencesCrimeStore(context);
            instance = new RoomCrimeStore(context);
        }
        return instance;
    }

}
