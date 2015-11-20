package com.offsec.nethunter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class KaliLauncherFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "KaliLauncherFragment";
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    public KaliLauncherFragment() {

    }

    public static KaliLauncherFragment newInstance(int sectionNumber) {
        KaliLauncherFragment fragment = new KaliLauncherFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.kali_launcher, container, false);
        addClickListener(R.id.button_start_kali, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_NH(""); // pops kali term.
            }
        }, rootView);
        addClickListener(R.id.button_start_su, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_SU(""); // pops SU shell in term
            }
        }, rootView);
        addClickListener(R.id.button_start_android, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_ANDROID(""); // pops andrid default shell
            }
        }, rootView);
        /**
         * Launch Kali menu
         */
        addClickListener(R.id.button_start_kalimenu, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_NH("kalimenu"); // since is a kali command we can send it as is
            }
        }, rootView);
        /**
         * Update Kali chroot
         */
        addClickListener(R.id.update_kali_chroot, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_NH("/usr/bin/start-update.sh");  // file in kali, exec it
            }
        }, rootView);
        /**
         * Launch Wifite
         */
        addClickListener(R.id.button_launch_wifite, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_SU("bootkali wifite"); // todo move out bootkali
            }
        }, rootView);
        /**
         * Turn off external wifi
         */
        addClickListener(R.id.turn_off_external_wifi, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_SU("bootkali wifi-disable"); // todo move out bootkali
            }
        }, rootView);
        /**
         * Turn off external wifi
         */
        addClickListener(R.id.kali_dumpmifare, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener_SU("bootkali dumpmifare"); // todo move out bootkali
            }
        }, rootView);

        return rootView;
    }

/*   public void getTerminalApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=jackpal.androidterm"))); // todo move out jackpal link
        } catch (android.content.ActivityNotFoundException anfe2) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=jackpal.androidterm"))); // todo move out jackpal link
        }
    }
*/
    private void addClickListener(int buttonId, View.OnClickListener onClickListener, View rootView) {
        rootView.findViewById(buttonId).setOnClickListener(onClickListener);
    }

    private void intentClickListener_ANDROID(final String command) {
        try {
            Intent intent =
                    new Intent("com.offsec.nhterm.RUN_SCRIPT");
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            intent.putExtra("com.offsec.nhterm.iInitialCommand", command);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_install_terminal), Toast.LENGTH_SHORT).show();
            //getTerminalApp();
        }
    }
    private void intentClickListener_SU(final String command) {
        try {
            Intent intent =
                    new Intent("com.offsec.nhterm.RUN_SCRIPT_SU");
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            intent.putExtra("com.offsec.nhterm.iInitialCommand", command);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_install_terminal), Toast.LENGTH_SHORT).show();

        }
    }
    private void intentClickListener_NH(final String command) {
        try {
            Intent intent =
                    new Intent("com.offsec.nhterm.RUN_SCRIPT_NH");
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            intent.putExtra("com.offsec.nhterm.iInitialCommand", command);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_install_terminal), Toast.LENGTH_SHORT).show();

        }
    }

}
