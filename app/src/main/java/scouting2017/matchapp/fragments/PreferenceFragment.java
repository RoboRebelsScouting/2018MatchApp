package scouting2017.matchapp.fragments;

import android.os.Bundle;

import scouting2017.matchapp.R;

public class PreferenceFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);
    }
}