package com.example.criminalintent.feature.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.criminalintent.R;

public class CrimeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new CrimeListFragment())
                    .commit();
        }
    }
}
