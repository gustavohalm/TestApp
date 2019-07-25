package com.xbrain.testapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbrain.testapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntregaFragment extends Fragment {


    public EntregaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrega, container, false);
        return  view;
    }

}
