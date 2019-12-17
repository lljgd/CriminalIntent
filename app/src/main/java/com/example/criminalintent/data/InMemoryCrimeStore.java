package com.example.criminalintent.data;

import com.example.criminalintent.data.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

class InMemoryCrimeStore extends BaseCrimeStore {

    private final List<Crime> crimes;

    InMemoryCrimeStore() {
        this(new ArrayList<Crime>());
    }

    InMemoryCrimeStore(List<Crime> initialCrimes) {
        this.crimes = initialCrimes;
    }

    @Override
    public List<Crime> getCrimes() {
        return crimes;
    }

    @Override
    public Crime getById(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    @Override
    public void generateRandomCrime() {
        Random random = new Random();

        Crime crime = new Crime();

        crime.setTitle("Crime #" + random.nextInt());
        crime.setSolved(random.nextBoolean());

        crimes.add(crime);
        notifyListeners();
    }

    @Override
    public void deleteCrime(Crime crime) {
        crimes.remove(crime);
        notifyListeners();
    }

    @Override
    public void deleteCrime(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId() == id) {
                crimes.remove(crime);
                notifyListeners();
                break;
            }
        }
    }

    @Override
    public void resurrectCrime(Crime crime, int position) {
        crimes.add(position, crime);
        notifyListeners();
    }

//    @Override
//    public void update(Crime crime) {
//    //     Nothing to do here
//    }
}
