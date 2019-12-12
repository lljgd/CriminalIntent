package com.example.criminalintent.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalintent.model.CrimeStore;
import com.example.criminalintent.view.CrimeListAdapter;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.view.CrimeViewHolder;
import com.google.android.material.snackbar.Snackbar;

public class CrimeListFragment extends Fragment {

    // View
    private RecyclerView recyclerView;
    private CrimeListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
        adapter = new CrimeListAdapter(CrimeStore.getInstance().getCrimes(), itemListener);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        CrimeViewHolder crimeViewHolder = (CrimeViewHolder) viewHolder;
                        Crime crime = crimeViewHolder.getCrime();
                        deleteItem(crime, viewHolder.getAdapterPosition());
                    }
                });
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void deleteItem(final Crime crime, final int position) {
        CrimeStore.getInstance().deleteCrime(crime);
        Snackbar.make(recyclerView, R.string.snackbar_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CrimeStore.getInstance().resurrectCrim(crime, position);
                    }
                })
                .show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.crime_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            CrimeStore.getInstance().generateRandomCrime();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CrimeStore.getInstance().addListener(crimesListChangedListener);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        CrimeStore.getInstance().removeListener(crimesListChangedListener);
        super.onPause();
    }

    private final CrimeStore.Listener crimesListChangedListener = new CrimeStore.Listener() {
        @Override
        public void onCrimesListChanged() {
            adapter.notifyDataSetChanged();
        }
    };

    private final CrimeListAdapter.ItemListener itemListener = new CrimeListAdapter.ItemListener() {
        @Override
        public void onCrimeClicked(Crime crime) {
            Intent intent = CrimeActivity.makeIntent(getContext(), crime.getId());
            startActivity(intent);
        }

        @Override
        public void onCrimeLongClicked(Crime crime) {
            DeleteConfirmationDialogFragment dialogFragment =
                    DeleteConfirmationDialogFragment.makeInstance(crime.getId());
            dialogFragment.show(getFragmentManager(), null);
        }
    };
}
