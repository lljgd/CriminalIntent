package com.example.criminalintent.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {CrimeEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class CrimeDatabase extends RoomDatabase {

    public abstract CrimeDao crimeDao();

}