package com.example.criminalintent.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalintent.model.CrimeStore;
import com.example.criminalintent.view.CrimeListAdapter;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;

public class CrimeListFragment extends Fragment {

    // View
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crime_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CrimeListAdapter(
                CrimeStore.getInstance().getCrimes(),
                itemListener)
        );
    }

    private final CrimeListAdapter.ItemListener itemListener = new CrimeListAdapter.ItemListener() {
        @Override
        public void onCrimeClicked(Crime crime) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, CrimeFragment.makeInstance(crime.getId()))
                    .addToBackStack(null)
                    .commit();
        }
    };
}
