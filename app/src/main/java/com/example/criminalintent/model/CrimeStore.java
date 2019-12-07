package com.example.criminalintent.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeStore {

    // Singleton
    private static CrimeStore instance;

    private CrimeStore() { }

    public static CrimeStore getInstance() {
        if (instance == null) {
            instance = new CrimeStore();
        }
        return instance;
    }
    // End of Singleton

    private List<Crime> crimes = generateDemoCrimes();

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getById(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    private static List<Crime> generateDemoCrimes() {
        List<Crime> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Для каждого второго объекта mCrimes.add(crime);
            result.add(crime);
        }
        return result;
    }

}
