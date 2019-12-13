package com.example.criminalintent.feature.list;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.criminalintent.R;
import com.example.criminalintent.data.CrimeStoreProvider;

import java.util.UUID;

public class DeleteConfirmationDialogFragment extends DialogFragment {

    private static final String KEY_ID = "key_id";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage(R.string.dialog_delete_item_message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UUID idOfItemToDelete = (UUID) getArguments().getSerializable(KEY_ID);
                        CrimeStoreProvider.getInstance(getContext()).deleteCrime(idOfItemToDelete);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .create();
    }

    public static DeleteConfirmationDialogFragment makeInstance(UUID uuid) {
        DeleteConfirmationDialogFragment fragment = new DeleteConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_ID, uuid);
        fragment.setArguments(args);
        return fragment;
    }
}
