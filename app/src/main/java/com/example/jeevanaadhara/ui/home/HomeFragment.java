package com.example.jeevanaadhara.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.jeevanaadhara.R;
import com.example.jeevanaadhara.databinding.FragmentHomeBinding;
import com.example.jeevanaadhara.order_address.OrderAddressActivity;

public class HomeFragment extends Fragment {

    TextView click;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        click = view.findViewById(R.id.click);
        click.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), OrderAddressActivity.class));
        });

        return view;
    }


}