package com.wzl.bottomnavigationvpdemo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private static SecondFragment fragment = null;

    private SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    static synchronized SecondFragment newInstance(String name) {
        return fragment == null ? new SecondFragment() : fragment;
    }
}
