package com.example.criminalintent.data.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CrimeEntity {
    @PrimaryKey
    @NonNull
    public String id;
    public String title;
    public long date;
    public boolean solved;
}
