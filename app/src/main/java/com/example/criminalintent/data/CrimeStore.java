package com.example.criminalintent.data;

import com.example.criminalintent.data.model.Crime;

import java.util.List;
import java.util.UUID;

public interface CrimeStore {
    List<Crime> getCrimes();

    Crime getById(UUID id);

    void generateRandomCrime();

    void deleteCrime(Crime crime);

    void deleteCrime(UUID id);

    void resurrectCrime(Crime crime, int position);

//    void update(Crime crime);

    void addListener(Listener listener);

    void removeListener(Listener listener);

    interface Listener {
        void onCrimesListChanged();
    }
}
