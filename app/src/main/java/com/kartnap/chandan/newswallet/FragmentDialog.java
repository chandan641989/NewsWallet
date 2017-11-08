package com.kartnap.chandan.newswallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Chandan on 11/7/2017.
 */

public class FragmentDialog extends DialogFragment {
    public static FragmentDialog newInstance(int title) {
        FragmentDialog frag = new FragmentDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.logo)
                .setTitle(title)
                .setPositiveButton(R.string.alert_visit,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((Home)getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.alert_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((Home)getActivity()).doNegativeClick();
                            }
                        }
                )
                .create();
    }
}
