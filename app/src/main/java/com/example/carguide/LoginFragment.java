package com.example.carguide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carguide.databinding.FragmentLoginBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFragment extends Fragment {

private FragmentLoginBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    public boolean check ;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_LoginFragment_to_RegisterFragment);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String User_account = binding.EditUserName.getText().toString();
                String User_password = binding.EditUserPassword.getText().toString();
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Connection connection = MariaDBConnection.getConnection();
                        try {
                            Statement  statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("select * from users where account='"+User_account+"'"+"AND password='"+User_password+"'");
                            if( resultSet.first()) {
                                //TODO: If DB has data, then pass
                                System.out.println("Login OK");
                                check = true;
                            }else {
                                //TODO: or deny
                                System.out.println("Login failed");

                                check = false;

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();

                        }
                    }
                });
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getDialog(check);

            }
        });

    }
    public void getDialog(boolean check) {
        System.out.println(check);
        AlertDialog.Builder dialogregister = new AlertDialog.Builder(getActivity());
        dialogregister.setCancelable(false);
        if (check==true){
            dialogregister.setTitle("登入成功");
            dialogregister.setMessage("登入成功");
            dialogregister.setNegativeButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_LoginFragment_to_HomeFragment);
                }
            });
        }else {
            dialogregister.setTitle("登入失敗");
            dialogregister.setMessage("登入失敗");
            dialogregister.setNegativeButton("重新登入", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            });
        }


        dialogregister.create().show();
    }

}