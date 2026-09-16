package fr.charleslabs.tinwhistletabs.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import fr.charleslabs.tinwhistletabs.R;

public class AppCreditsDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View contentView = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_about, null, false);

        TextView message = contentView.findViewById(R.id.aboutDialog_message);
        message.setMovementMethod(LinkMovementMethod.getInstance());

        contentView.findViewById(R.id.aboutDialog_close)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

        return new AlertDialog.Builder(requireActivity())
                .setView(contentView)
                .setCancelable(true)
                .create();
    }
}