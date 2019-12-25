package com.example.criminalintent.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.criminalintent.R;
import com.example.criminalintent.data.CrimeStoreProvider;
import com.example.criminalintent.data.model.Crime;

import java.util.List;
import java.util.UUID;

public class CrimeActivity extends AppCompatActivity {

    private static final String KEY_ID = "key_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_details);

        ViewPager2 viewPager = findViewById(R.id.pager);

        viewPager.setAdapter(new CrimePagesAdapter(
                CrimeStoreProvider.getInstance(this).getCrimes(),
                this
        ));
    }

    public static Intent makeIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(KEY_ID, crimeId);
        return intent;
    }
}
