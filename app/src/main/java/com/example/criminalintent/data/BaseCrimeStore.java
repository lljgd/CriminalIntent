package com.example.criminalintent.data;

import com.example.criminalintent.data.model.Crime;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class BaseCrimeStore implements CrimeStore {

    private final Set<Listener> listenersSet = new HashSet<>();

    protected final void notifyListeners() {
        for (Listener listener : listenersSet) {
            listener.onCrimesListChanged();
        }
    }

    @Override
    public final void addListener(Listener listener) {
        listenersSet.add(listener);
    }

    @Override
    public final void removeListener(Listener listener) {
        listenersSet.remove(listener);
    }

    protected static Crime makeRandomCrime() {
        Random random = new Random();

        Crime crime = new Crime();

        crime.setTitle("Crime #" + random.nextInt());
        crime.setSolved(random.nextBoolean());

        return crime;
    }
}
