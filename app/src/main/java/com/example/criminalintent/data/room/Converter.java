package com.example.criminalintent.data.room;

import com.example.criminalintent.data.model.Crime;

import java.util.Date;
import java.util.UUID;

class Converter {
    static Crime convert(CrimeEntity entity) {
        Crime crime = new Crime();

        crime.setId(UUID.fromString(entity.id));
        crime.setTitle(entity.title);
        crime.setDate(new Date(entity.date));
        crime.setSolved(entity.solved);

        return crime;
    }

    static CrimeEntity convert(Crime crime) {
        CrimeEntity crimeEntity = new CrimeEntity();

        crimeEntity.id = crime.getId().toString();
        crimeEntity.title = crime.getTitle();
        crimeEntity.date = crime.getDate().getTime();
        crimeEntity.solved = crime.isSolved();

        return crimeEntity;
    }
}
