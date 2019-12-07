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

    private TextView titleView;
    private TextView dateView;
    private CheckBox solvedCheckBox;

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(
                    v.getContext(),
                    crime.getTitle() + " was clicked",
                    Toast.LENGTH_SHORT).show();
        }
    };

    public CrimeViewHolder(@NonNull View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.title);
        dateView = itemView.findViewById(R.id.date);
        solvedCheckBox = itemView.findViewById(R.id.solved);

        itemView.setOnClickListener(clickListener);
    }

    public void bindTo(Crime crime) {
        this.crime = crime;

        titleView.setText(crime.getTitle());
        dateView.setText(crime.getDate().toString());
        solvedCheckBox.setChecked(crime.isSolved());
    }
}
