package com.example.mymusicapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicapp.MainActivity;
import com.example.mymusicapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class SignUpFragment extends Fragment {

    private TextView already_have_account;

    private FrameLayout frameLayout;

    private EditText username;
    private EditText email2;
    private EditText password2;
    private EditText conformpassword;
    private Button buttonsingup;


    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        already_have_account = view.findViewById(R.id.singintext);
        frameLayout = requireActivity().findViewById(R.id.registar_frame_layout);

        username = view.findViewById(R.id.username);
        email2 = view.findViewById(R.id.email2);
        password2 = view.findViewById(R.id.password2);
        conformpassword= view.findViewById(R.id.conformpassword);
        buttonsingup=view.findViewById(R.id.Buttonsingup);
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        already_have_account.setOnClickListener(view12 -> setFragment( new SignInFragment()));

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });

        email2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        conformpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        buttonsingup.setOnClickListener(view1 -> {
            singupWithFirebace();
            buttonsingup.setEnabled(false);
            buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
        });
    }

    private void singupWithFirebace() {
        if (email2.getText().toString().matches(getString(R.string.a_za_z0_9_a_z_a_z))){
            if (password2.getText().toString().equals(conformpassword.getText().toString())){

                mAuth.createUserWithEmailAndPassword(email2.getText().toString(),password2.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(getActivity() , MainActivity.class);
                                requireActivity().startActivity(intent);
                                requireActivity().finish();

                            }else {
                                Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                buttonsingup.setEnabled(false);
                                buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
                            }
                        });

            }else {
                conformpassword.setError("password doesn't match.");
                buttonsingup.setEnabled(false);
                buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
            }
        }else {
            email2.setError("Invalid Email pattern!");
            buttonsingup.setEnabled(false);
            buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
        }
    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,R.anim.out_from_right);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();

    }
    private void checkInputs(){
        if (username.getText().toString().isEmpty()){
           if (!email2.getText().toString().isEmpty()){
               if (!password2.getText().toString().isEmpty()&& password2.getText().toString().length() >=6){
                   if (!conformpassword.getText().toString().isEmpty()){
                       buttonsingup.setEnabled(false);
                       buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));

                   }else {
                       buttonsingup.setEnabled(false);
                       buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
                   }
               }else {
                   buttonsingup.setEnabled(false);
                   buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
               }
           }else {
               buttonsingup.setEnabled(false);
               buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
           }
        }else {
            buttonsingup.setEnabled(false);
            buttonsingup.setTextColor(getResources().getColor(R.color.taranswhite));
        }

    }

}