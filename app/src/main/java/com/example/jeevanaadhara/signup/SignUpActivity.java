package com.example.jeevanaadhara.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeevanaadhara.R;
import com.example.jeevanaadhara.databinding.ActivitySignUpBinding;
import com.example.jeevanaadhara.order_address.OrderAddressActivity;
import com.example.jeevanaadhara.signin.SignInActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
private ActivitySignUpBinding signUpBinding;
    FirebaseAuth mAuth;
    String mobilenum, verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());
        mAuth = FirebaseAuth.getInstance();


        signUpBinding.textVerify.setOnClickListener(view -> {
            checkValidation(signUpBinding.edFullName.getText().toString().trim(),signUpBinding.edMobile.getText().toString().trim()
            ,signUpBinding.edEmail.getText().toString().trim(),signUpBinding.edPassword.getText().toString().trim());
        });

        signUpBinding.textSubmitOtp.setOnClickListener(view -> {
            if(TextUtils.isEmpty(signUpBinding.edOtp.getText().toString())){
                Toast.makeText(this, "wrong otp empty", Toast.LENGTH_SHORT).show();

            }else {
                verifyCode(signUpBinding.edOtp.getText().toString());
            }
        });


        signUpBinding.tvSignIn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        });
    }


    public void checkValidation(String name, String mobile,
                                String email,String password) {
        if (TextUtils.isEmpty(signUpBinding.edFullName.getText().toString())) {
            signUpBinding.edFullName.setError(getString(R.string.please_enter_value));
            signUpBinding.edFullName.requestFocus();
        }   else if (TextUtils.isEmpty(signUpBinding.edEmail.getText().toString())) {
            signUpBinding.edEmail.setError(getString(R.string.please_enter_value));
            signUpBinding.edEmail.requestFocus();
        } else if (signUpBinding.edMobile.getText().toString().length() < 10) {
            signUpBinding.edMobile.setError(getString(R.string.please_enter_valid_number));
            signUpBinding.edMobile.requestFocus();
        }

        else if (TextUtils.isEmpty(signUpBinding.edPassword.getText().toString())) {
            signUpBinding.edPassword.setError(getString(R.string.please_enter_password));
            signUpBinding.edPassword.requestFocus();
        }  else {

//            if (Utility.isConnected(SignUpActivity.this)) {
//                callApi(checkmNumber,checkPassword,eemail,laname,finame);
//            } else {
//                Toast.makeText(this, R.string.error_no_internet, Toast.LENGTH_SHORT).show();
//            }

            SendVerificationCode(mobile);
            signUpBinding.verifyOtpLinera.setVisibility(View.VISIBLE);

        }
    }

    private void SendVerificationCode(String mobile) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(SignUpActivity.this, "Verificatoin failed", Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            verificationId = s;
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
        SignByCreditial(credential);
    }

    private void SignByCreditial(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();

                    }
                } );
    }
}