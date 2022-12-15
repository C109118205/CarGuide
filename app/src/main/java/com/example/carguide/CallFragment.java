package com.example.carguide;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carguide.databinding.FragmentCallBinding;

public class CallFragment extends Fragment {

    private FragmentCallBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentCallBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonYeshurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("tw.gov.npa.videocall");
                if (launchIntent != null){
                    startActivity( launchIntent );
                }else{
                    Uri uri = Uri.parse( "market://details?id=" + "tw.gov.npa.videocall" );
                    Intent intent = new Intent( Intent.ACTION_VIEW, uri );
                    startActivity( intent );
                }

            }
        });
        binding.buttonNohunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CallFragment.this)
                        .navigate(R.id.action_CallFragment_to_PhotoFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}