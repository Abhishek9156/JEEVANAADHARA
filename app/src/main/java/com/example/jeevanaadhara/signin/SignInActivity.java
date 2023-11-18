package com.example.jeevanaadhara.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.jeevanaadhara.MainActivity;
import com.example.jeevanaadhara.R;
import com.example.jeevanaadhara.databinding.ActivitySignInBinding;
import com.example.jeevanaadhara.forgotpassword.ForgotPasswordActivity;
import com.example.jeevanaadhara.map.DriverMapsActivity;
import com.example.jeevanaadhara.map.MapsActivity;
import com.example.jeevanaadhara.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity {
    TextView text_login, text_signup;
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tvSignup.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });
        binding.tvLogin.setOnClickListener(view -> {
                    checkValidation();
                }
        );
        binding.tvForgotPass.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
        });
    }

    private void checkValidation() {
        if (binding.edNumber.getText().toString().length() < 10) {
            binding.edNumber.setError(getString(R.string.please_enter_valid_number));
            binding.edNumber.requestFocus();
        } else if (TextUtils.isEmpty(binding.edPassword.getText().toString().trim())) {
            binding.edPassword.setError(getString(R.string.please_enter_password));
            binding.edPassword.requestFocus();
        } else
            startActivity(new Intent(getApplicationContext(), DriverMapsActivity.class));
    }
}