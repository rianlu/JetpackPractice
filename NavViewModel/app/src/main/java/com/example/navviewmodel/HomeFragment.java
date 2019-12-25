package com.example.navviewmodel;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.example.navviewmodel.databinding.FragmentHomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    MyViewModel viewModel;
    private String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getActivity());

        //binding.button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment));
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        /*requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NavController controller = Navigation.findNavController(v);
                                controller.navigate(R.id.action_homeFragment_to_detailFragment);
                            }
                        });*/
                        Log.d(TAG, "run: " + v.getId());
                        startActivity(new Intent(requireActivity(), Main2Activity.class));
                    }
                }).start();
            }
        });
        binding.seekBar.setProgress(viewModel.getNumber().getValue());
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.getNumber().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return binding.getRoot();
    }

}
