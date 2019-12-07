package com.example.criminalintent.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;

class CrimeViewHolder extends RecyclerView.ViewHolder {

    private Crime crime;

    private CrimeListAdapter.ItemListener itemListener;

    private TextView titleView;
    private TextView dateView;
    private CheckBox solvedCheckBox;

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            itemListener.onCrimeClicked(crime);
        }
    };

    public CrimeViewHolder(@NonNull View itemView, CrimeListAdapter.ItemListener itemListener) {
        super(itemView);

        titleView = itemView.findViewById(R.id.title);
        dateView = itemView.findViewById(R.id.date);
        solvedCheckBox = itemView.findViewById(R.id.solved);

        itemView.setOnClickListener(clickListener);

        this.itemListener = itemListener;
    }

    public void bindTo(Crime crime) {
        this.crime = crime;

        titleView.setText(crime.getTitle());
        dateView.setText(crime.getDate().toString());
        solvedCheckBox.setChecked(crime.isSolved());
    }
}
