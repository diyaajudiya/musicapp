package com.example.mymusicapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicapp.R;
import com.google.android.material.textfield.TextInputEditText;


public class SignInFragment extends Fragment {

    private TextView Donthaveanaccount;
    private TextView resetpassword;
    private Button Button;
    TextInputEditText email,password;


    private FrameLayout frameLayout;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Donthaveanaccount = view.findViewById(R.id.dont_have_an_account);
        resetpassword = view.findViewById(R.id.resetpassword);
        Button= view.findViewById(R.id.singin);
        email=view.findViewById(R.id.email2);
        password = view.findViewById(R.id.password2);


        frameLayout = requireActivity().findViewById(R.id.registar_frame_layout);
        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Donthaveanaccount.setOnClickListener(view12 -> setFragment(new SignUpFragment()));


   
        resetpassword.setOnClickListener(view1 -> setFragment(new ResetPasswordFragment()));

        Button.setOnClickListener(view13 -> {
            String textview1;
            textview1 = String.valueOf(email.getText());

            TextUtils.isEmpty(textview1);

        });


    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction =  requireActivity(). getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,R.anim.out_from_left);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();




    }


}