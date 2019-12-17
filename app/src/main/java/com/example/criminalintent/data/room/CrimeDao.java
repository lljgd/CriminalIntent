package com.example.criminalintent.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CrimeDao {

    @Query("SELECT * FROM CrimeEntity")
    List<CrimeEntity> getAllCrimes();


    @Query("SELECT * FROM CrimeEntity WHERE id == :idParam")
    CrimeEntity getCrimeById(String idParam);


    @Insert
    void add(CrimeEntity crimeEntity);


    @Delete
    void delete(CrimeEntity crimeEntity);


    @Update
    void update(CrimeEntity crimeEntity);

}