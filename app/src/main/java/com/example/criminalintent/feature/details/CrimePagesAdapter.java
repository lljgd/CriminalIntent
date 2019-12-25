package com.example.criminalintent.feature.details;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.criminalintent.data.model.Crime;

import java.util.List;

class CrimePagesAdapter extends FragmentStateAdapter {

    private final List<Crime> crimes;

    CrimePagesAdapter(List<Crime> crimes, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CrimeFragment.makeInstance(crimes.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }
}
