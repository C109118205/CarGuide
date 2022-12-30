package com.example.carguide;


import android.accessibilityservice.GestureDescription;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.carguide.databinding.FragmentRegisterBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegisterFragment extends Fragment {
private FragmentRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString();
                int selectedgender = binding.radioGroup.getCheckedRadioButtonId();
                String selectString = getResources().getResourceEntryName(selectedgender);
                String phone = binding.Phone.getText().toString();
                String address =binding.address.getText().toString();
                String account = binding.account.getText().toString();
                String password = binding.password.getText().toString();
                getDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Connection connection = MariaDBConnection.getConnection();
                        String sql = "Insert into users(name,gender,phone,address,account,password,identity) Values(?,?,?,?,?,?,?)";
                        try {
                            PreparedStatement statement = connection.prepareStatement(sql);
                            statement.setString(1,name);
                            statement.setString(2,selectString);
                            statement.setString(3,phone);
                            statement.setString(4,address);
                            statement.setString(5,account);
                            statement.setString(6,password);
                            statement.setString(7,"1");
                            int rowsInserted = statement.executeUpdate();

                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();
            }

        });
    }
    private void getDialog() {
        AlertDialog.Builder dialogregister = new AlertDialog.Builder(getActivity());
        dialogregister.setCancelable(false);
        dialogregister.setTitle("註冊成功");
        dialogregister.setMessage("註冊成功");
        dialogregister.setNegativeButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_RegisterFragment_to_LoginFragment);
            }
        });
       dialogregister.create().show();
    }

}