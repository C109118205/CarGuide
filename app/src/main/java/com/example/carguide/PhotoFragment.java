package com.example.carguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.carguide.databinding.FragmentPhotoBinding;


public class PhotoFragment extends Fragment {
    private FragmentPhotoBinding binding;

    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final String[] storage_PERMISSION = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE = 1001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.strike1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(checkpermission()){
                   enableCamera();
               }
            }
        });
        binding.strike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpermission()){
                    enableCamera();
                }
            }
        });
        binding.strike3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpermission()){
                    enableCamera();
                }
            }
        });
        binding.strike4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpermission()){
                    enableCamera();
                }
            }
        });
        binding.strike5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkpermission()){
                    enableCamera();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private  boolean checkpermission(){

        if(hasCameraPermission()) {
            if (hasStoragePermission()){
                return true;
            }else {
                Toast.makeText(getActivity(), "storage permission failed", Toast.LENGTH_SHORT).show();
                requeStstoragePermission();
            }
        } else {
            Toast.makeText(getActivity(), "camera permission failed", Toast.LENGTH_SHORT).show();
            requestCameraPermission();
        }
        return false;
    }
    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
    private boolean hasStoragePermission() {
        return ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(getActivity(),CAMERA_PERMISSION,REQUEST_CODE);

    }
    private void requeStstoragePermission() {
        ActivityCompat.requestPermissions(getActivity(),storage_PERMISSION,REQUEST_CODE);
    }
    private void enableCamera() {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivity(intent);
    }



}