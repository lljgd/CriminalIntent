package com.example.criminalintent.feature.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalintent.R;
import com.example.criminalintent.data.model.Crime;

import java.util.List;

public class CrimeListAdapter extends RecyclerView.Adapter<CrimeViewHolder> {

    private List<Crime> crimes;
    private ItemListener itemListener;

    public CrimeListAdapter(
            List<Crime> crimes,
            ItemListener itemListener) {
        this.crimes = crimes;
        this.itemListener = itemListener;

        setHasStableIds(true);
    }

    public void submitList(List<Crime> newList) {
        this.crimes = newList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return crimes.get(position).getId().hashCode();
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_crime, parent, false);

        return new CrimeViewHolder(itemView, itemListener);
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

    public interface ItemListener {
        void onCrimeClicked(Crime crime);
        void onCrimeLongClicked(Crime crime);
    }
}
