package com.pedroruiz.androidutilities.permissions;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ExplanationPermissonDialog extends DialogFragment {

    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_POSITIVE_BUTTON_LISTENER = "positiveButtonListener";

    private String message;
    private DialogInterface.OnClickListener positiveButtonListener;

    public static ExplanationPermissonDialog newInstance(Bundle args){
        ExplanationPermissonDialog d = new ExplanationPermissonDialog();
        d.setArguments(args);
        return d;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        Bundle extras = getArguments();
        if(extras != null){
            message = extras.getString(EXTRA_MESSAGE, "");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(positiveButtonListener != null)
            builder.setMessage(message).setPositiveButton(android.R.string.ok, positiveButtonListener);
        else
            builder.setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setPositiveButtonListener(DialogInterface.OnClickListener listener){
        this.positiveButtonListener = listener;
    }


}
