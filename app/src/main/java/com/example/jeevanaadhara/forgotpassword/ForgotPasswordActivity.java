package com.example.jeevanaadhara.forgotpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.jeevanaadhara.R;
import com.example.jeevanaadhara.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvVerifymobile.setOnClickListener(view -> {
            checkValidation();
        });


    }

    private void checkValidation() {
        if(binding.edMobile.getText().length()<10){
            binding.edMobile.setError(getString(R.string.please_enter_valid_number));
            binding.edMobile.requestFocus();
        }else{
            binding.verifyOtpLinera.setVisibility(View.VISIBLE);
        }
    }
}