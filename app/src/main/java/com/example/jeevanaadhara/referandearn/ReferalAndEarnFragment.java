package com.example.jeevanaadhara.referandearn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeevanaadhara.R;


public class ReferalAndEarnFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View   view = inflater.inflate(R.layout.fragment_referal_and_earn, container, false);
        return  view;
    }
}