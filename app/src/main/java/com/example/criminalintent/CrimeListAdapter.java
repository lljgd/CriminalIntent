package com.example.criminalintent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListAdapter extends RecyclerView.Adapter<CrimeViewHolder> {

    private List<Crime> crimes;

    public CrimeListAdapter(List<Crime> crimes) {
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_crime, parent, false);

        return new CrimeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeViewHolder holder, int position) {
        Crime crime = crimes.get(position);
        holder.bindTo(crime);
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }
}

class CrimeViewHolder extends RecyclerView.ViewHolder {

    private Crime crime;

    private TextView titleView;
    private TextView dateView;
    private CheckBox solvedCheckBox;

    public CrimeViewHolder(@NonNull View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.title);
        dateView = itemView.findViewById(R.id.date);
        solvedCheckBox = itemView.findViewById(R.id.solved);
    }

    public void bindTo(Crime crime) {
        this.crime = crime;

        titleView.setText(crime.getTitle());
        dateView.setText(crime.getDate().toString());
        solvedCheckBox.setChecked(crime.isSolved());
    }
}
