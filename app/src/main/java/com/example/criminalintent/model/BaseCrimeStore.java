package com.example.criminalintent.model;

import java.util.HashSet;
import java.util.Set;

abstract class BaseCrimeStore implements CrimeStore {

    private final Set<Listener> listenersSet = new HashSet<>();

    final void notifyListeners() {
        for (Listener listener : listenersSet) {
            listener.onCrimesListChanged();
        }
    }


    @Override
    public void addListener(Listener listener) {
        listenersSet.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listenersSet.remove(listener);
    }
}
