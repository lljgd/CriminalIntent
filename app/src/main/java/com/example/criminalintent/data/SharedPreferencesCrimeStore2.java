package com.example.criminalintent.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.criminalintent.data.model.Crime;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


class SharedPreferencesCrimeStore2 implements CrimeStore {

    private final SharedPreferences sharedPreferences;

    private final InMemoryCrimeStore inMemoryCrimeStore;

    SharedPreferencesCrimeStore2(Context context) {
        sharedPreferences = context.getSharedPreferences("crimeStore.prefs", Context.MODE_PRIVATE);
        inMemoryCrimeStore = new InMemoryCrimeStore(readSavedCrimes());
    }

    private List<Crime> readSavedCrimes() {
        String savedEncodedList = sharedPreferences.getString("KEY", null);
        if (savedEncodedList == null) {
            return new ArrayList<>();
        }
        Type collectionType = new TypeToken<List<Crime>>(){}.getType();
        return new Gson().fromJson(savedEncodedList, collectionType);
    }

    private void saveCurrentCrimes() {
        List<Crime> crimes = inMemoryCrimeStore.getCrimes();
        sharedPreferences.edit().putString("KEY", new Gson().toJson(crimes)).apply();
    }

    @Override
    public List<Crime> getCrimes() {
        return inMemoryCrimeStore.getCrimes();
    }

    @Override
    public Crime getById(UUID id) {
        return inMemoryCrimeStore.getById(id);
    }

    @Override
    public void generateRandomCrime() {
        inMemoryCrimeStore.generateRandomCrime();
        saveCurrentCrimes();
    }

    @Override
    public void deleteCrime(Crime crime) {
        inMemoryCrimeStore.deleteCrime(crime);
        saveCurrentCrimes();
    }

    @Override
    public void deleteCrime(UUID id) {
        inMemoryCrimeStore.deleteCrime(id);
        saveCurrentCrimes();
    }

    @Override
    public void resurrectCrime(Crime crime, int position) {
        inMemoryCrimeStore.resurrectCrime(crime, position);
        saveCurrentCrimes();
    }

//    @Override
//    public void update(Crime crime) {
//        saveCurrentCrimes();
//    }

    @Override
    public void addListener(Listener listener) {
        inMemoryCrimeStore.addListener(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        inMemoryCrimeStore.removeListener(listener);
    }
}
