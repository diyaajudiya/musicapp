package com.example.mymusicapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicapp.MainActivity;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class SignInFragment extends Fragment {

    private TextView Donthaveanaccount;
    private TextView resetpassword;
    private Button Button;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    private FrameLayout frameLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Donthaveanaccount = view.findViewById(R.id.dont_have_an_account);
        resetpassword = view.findViewById(R.id.resetpassword);
        Button = view.findViewById(R.id.singin);
        email = view.findViewById(R.id.email2);
        password = view.findViewById(R.id.password2);
        progressBar = view.findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


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

        email.addTextChangedListener(new TextWatcher() {
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

        password.addTextChangedListener(new TextWatcher() {
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

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button.setEnabled(false);
                Button.setTextColor(getResources().getColor(R.color.taranswhite));
                signInWithFirebase();

            }
        });


    }

    private void signInWithFirebase() {
        if (email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.INVISIBLE);
                           if (task.isSuccessful()){
                               Intent intent = new Intent(getActivity() , MainActivity.class);
                               requireActivity().startActivity(intent);
                               requireActivity().finish();

                           }else {
                               Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                               Button.setEnabled(false);
                               Button.setTextColor(getResources().getColor(R.color.taranswhite));
                           }
                        }
                    });


        }else

    {
        email.setError("Invalid Email pattern!");
        Button.setEnabled(false);
        Button.setTextColor(getResources().getColor(R.color.taranswhite));
    }

}


    private void checkInputs() {
        if (!email.getText().toString().isEmpty()){
            if (!password.getText().toString().isEmpty()){
                Button.setEnabled(true);
                Button.setTextColor(getResources().getColor(R.color.white));

            }else {
                Button.setEnabled(false);
                Button.setTextColor(getResources().getColor(R.color.taranswhite));
            }
        }else {
            Button.setEnabled(false);
            Button.setTextColor(getResources().getColor(R.color.taranswhite));
        }
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction =  requireActivity(). getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_right,R.anim.out_from_left);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();




    }


}