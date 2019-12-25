package com.example.navviewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.navviewmodel.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;
    MyViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getActivity());

        binding.button3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_homeFragment));
        return binding.getRoot();
    }
}
