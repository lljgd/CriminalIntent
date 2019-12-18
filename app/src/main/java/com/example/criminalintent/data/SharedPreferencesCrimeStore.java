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

public class SharedPreferencesCrimeStore extends BaseCrimeStore {

    private static final String KEY_CRIMES = "key_crimes";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesCrimeStore(Context context) {
        sharedPreferences = context.getSharedPreferences(
                "crime-store.prefs", Context.MODE_PRIVATE);
    }

    @Override
    public List<Crime> getCrimes() {
        String encodedCrimes = sharedPreferences.getString(KEY_CRIMES, null);

        if (encodedCrimes == null) {
            return new ArrayList<>();
        }

        Type crimesListType = new TypeToken<List<Crime>>(){}.getType();
        List<Crime> result = new Gson().fromJson(encodedCrimes, crimesListType);

        return result;
    }

    @Override
    public Crime getById(UUID id) {
        List<Crime> crimes = getCrimes();
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    @Override
    public void generateRandomCrime() {
        List<Crime> crimes = getCrimes();
        crimes.add(makeRandomCrime());
        saveCrimes(crimes);
        notifyListeners();
    }

    @Override
    public void deleteCrime(Crime crimeToDelete) {
        deleteCrime(crimeToDelete.getId());
    }

    @Override
    public void deleteCrime(UUID id) {
        List<Crime> crimes = getCrimes();
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                crimes.remove(crime);
                saveCrimes(crimes);
                notifyListeners();
                return;
            }
        }
    }

    @Override
    public void resurrectCrime(Crime crime, int position) {
        List<Crime> crimes = getCrimes();
        crimes.add(position, crime);
        saveCrimes(crimes);
        notifyListeners();
    }

    private void saveCrimes(List<Crime> crimesToSave) {
        String encodedCrimes = new Gson().toJson(crimesToSave);
        sharedPreferences.edit()
                .putString(KEY_CRIMES, encodedCrimes)
                .apply();
    }
}
