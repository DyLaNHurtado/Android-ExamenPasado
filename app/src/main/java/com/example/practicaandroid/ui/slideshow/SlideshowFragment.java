package com.example.practicaandroid.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.practicaandroid.R;
import com.example.practicaandroid.databinding.FragmentHomeBinding;
import com.example.practicaandroid.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvReceta.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvReceta.setAdapter(new RecetaAdapter(getActivity()));
        onClickListener();

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.rvReceta.setAdapter(new RecetaAdapter(getActivity()));
    }

    private void onClickListener(){
        binding.buttonNewReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NuevaReserva.class);
                startActivity(intent);
            }
        });
    }

}