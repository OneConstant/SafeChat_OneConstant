package com.oneconstant.safechat.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.oneconstant.safechat.R;
import com.oneconstant.safechat.databinding.ActivityFingerIdBinding;
import com.oneconstant.safechat.databinding.ActivityLoginBinding;
import com.oneconstant.safechat.screens.Main;

import java.util.concurrent.Executor;

public class FingerID extends AppCompatActivity {

    private ActivityFingerIdBinding mBinding;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding = ActivityFingerIdBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(FingerID.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
//                mBinding.authStatusTV.setText("Authentication Canceled");
                Toast.makeText(FingerID.this, "Authentication Error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
//                mBinding.authStatusTV.setText("Authentication Succeed...!");
                Toast.makeText(FingerID.this, "Authentication Succeed...!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FingerID.this, Main.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
//                mBinding.authStatusTV.setText("Authentication Failed...!");
                Toast.makeText(FingerID.this, "Authentication Failed...!", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using Fingerprint")
                .setNegativeButtonText("Skip")
                .build();
//
//        promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Authenticate with Face")
//                .setNegativeButtonText("Cancel")
//                .setConfirmationRequired(true)
//                .build();

        mBinding.fingerId.setOnClickListener(v -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }
}