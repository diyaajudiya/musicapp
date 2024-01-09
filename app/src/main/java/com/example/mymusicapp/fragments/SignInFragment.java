package com.example.mymusicapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicapp.R;


public class SignInFragment extends Fragment {

    private TextView Donthaveanaccount;
    private TextView resetpassword;

    private FrameLayout frameLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Donthaveanaccount = view.findViewById(R.id.dont_have_an_account);
        resetpassword = view.findViewById(R.id.resetpassword);


        frameLayout = getActivity().findViewById(R.id.registar_frame_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Donthaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());

            }
        });

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setFragment(new ResetPasswordFragment());
            }
        });


    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction =  getActivity() . getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();

    }


}