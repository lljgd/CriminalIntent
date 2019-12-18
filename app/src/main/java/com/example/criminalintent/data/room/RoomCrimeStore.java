package com.example.criminalintent.data.room;

import android.content.Context;

import androidx.room.Room;

import com.example.criminalintent.data.BaseCrimeStore;
import com.example.criminalintent.data.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomCrimeStore extends BaseCrimeStore {

    private CrimeDao crimeDao;

    public RoomCrimeStore(Context context) {
        crimeDao = Room
                .databaseBuilder(
                        context,
                        CrimeDatabase.class,
                        "crime-database.sqlite")
                .allowMainThreadQueries()
                .build()
                .crimeDao();
    }

    @Override
    public List<Crime> getCrimes() {
        List<CrimeEntity> crimeEntityList = crimeDao.getAllCrimes();

        List<Crime> resultList = new ArrayList<>();

        for (CrimeEntity crimeEntity : crimeEntityList) {
            resultList.add(Converter.convert(crimeEntity));
        }

        return resultList;
    }

    @Override
    public Crime getById(UUID id) {
        CrimeEntity crimeEntity = crimeDao.getCrimeById(id.toString());

        return Converter.convert(crimeEntity);
    }

    @Override
    public void generateRandomCrime() {
        Crime crime = makeRandomCrime();
        crimeDao.add(Converter.convert(crime));

        notifyListeners();
    }

    @Override
    public void deleteCrime(Crime crime) {
        crimeDao.delete(Converter.convert(crime));
        notifyListeners();
    }

    @Override
    public void deleteCrime(UUID id) {
        CrimeEntity entityToDelete = new CrimeEntity();

        entityToDelete.id = id.toString();
        crimeDao.delete(entityToDelete);

        notifyListeners();
    }

    @Override
    public void resurrectCrime(Crime crime, int position) {
        crimeDao.add(Converter.convert(crime));
        notifyListeners();
    }
}
