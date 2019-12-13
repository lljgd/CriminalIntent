package com.example.criminalintent.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.criminalintent.R;

import java.util.UUID;

public class CrimeActivity extends AppCompatActivity {

    private static final String KEY_ID = "key_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            UUID crimeId = (UUID) getIntent().getSerializableExtra(KEY_ID);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, CrimeFragment.makeInstance(crimeId))
                    .commit();
        }
    }

    public static Intent makeIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(KEY_ID, crimeId);
        return intent;
    }
}
