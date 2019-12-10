package com.example.criminalintent.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

    private List<Crime> crimes = new ArrayList<>();

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

    public void generateRandomCrime() {
        Random random = new Random();

        Crime crime = new Crime();

        crime.setTitle("Crime #" + random.nextInt());
        crime.setSolved(random.nextBoolean());

        crimes.add(crime);
        notifyListeners();
    }

    public void deleteCrime(Crime crime) {
        crimes.remove(crime);
        notifyListeners();
    }

    public void deleteCrime(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId() == id) {
                crimes.remove(crime);
                notifyListeners();
                break;
            }
        }
    }

    private void notifyListeners() {
        for (Listener listener : listenersSet) {
            listener.onCrimesListChanged();
        }
    }

    private final Set<Listener> listenersSet = new HashSet<>();

    public void addListener(Listener listener) {
        listenersSet.add(listener);
    }

    public void removeListener(Listener listener) {
        listenersSet.remove(listener);
    }

    public interface Listener {
        void onCrimesListChanged();
    }
}
