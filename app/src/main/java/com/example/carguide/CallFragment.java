package com.example.carguide;

import static android.graphics.Color.TRANSPARENT;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

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

                final Dialog alertDialog = new Dialog(getActivity());
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.call_dialog);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                alertDialog.show();

                Button cell_110 = (Button) alertDialog.findViewById(R.id.cell_110);
                Button cell_119 = (Button) alertDialog.findViewById(R.id.cell_119);
                Button video_call = (Button) alertDialog.findViewById(R.id.video_call);
                Button cancel = (Button) alertDialog.findViewById(R.id.call_cancel) ;

                cell_110.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:110"));
                        startActivity(intent);

                        Toast.makeText(getActivity(), "cell_110", Toast.LENGTH_SHORT).show();
                    }
                });
                cell_119.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:119"));
                        startActivity(intent);

                        Toast.makeText(getActivity(), "cell_119", Toast.LENGTH_SHORT).show();
                    }
                });
                video_call.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getActivity(), "video_call", Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }
        });
        binding.buttonNohunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog alertDialog = new Dialog(getActivity());
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setContentView(R.layout.call_dialog_nohunt);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                alertDialog.show();

                Button cell_110 = (Button) alertDialog.findViewById(R.id.cell_110);
                Button video_call = (Button) alertDialog.findViewById(R.id.video_call);
                Button cancel = (Button) alertDialog.findViewById(R.id.call_cancel) ;

                cell_110.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:110"));
                        startActivity(intent);

                        Toast.makeText(getActivity(), "cell_110", Toast.LENGTH_SHORT).show();
                    }
                });

                video_call.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getActivity(), "video_call", Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
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