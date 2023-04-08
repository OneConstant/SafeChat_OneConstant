package com.oneconstant.safechat.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.oneconstant.safechat.R;
import com.oneconstant.safechat.databinding.ActivityMainBinding;
import com.oneconstant.safechat.databinding.ActivityLoginBinding;
import com.oneconstant.safechat.screens.Main;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding mBinding;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Spannable word = new SpannableString("Safe");
        word.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.white)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBinding.appName.setText(word);
        Spannable word2 = new SpannableString("Chat");
        word2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.yellow)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mBinding.appName.append(word2);

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
//                mBinding.authStatusTV.setText("Authentication Canceled");
                Toast.makeText(Login.this, "Authentication Error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
//                mBinding.authStatusTV.setText("Authentication Succeed...!");
                Toast.makeText(Login.this, "Authentication Succeed...!", Toast.LENGTH_SHORT).show();
                addDataToFirestore();
                startActivity(new Intent(Login.this, Main.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
//                mBinding.authStatusTV.setText("Authentication Failed...!");
                Toast.makeText(Login.this, "Authentication Failed...!", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using Fingerprint")
                .setNegativeButtonText("Skip")
                .build();
//
//        mBinding.authBtn.setOnClickListener(v -> {
//            biometricPrompt.authenticate(promptInfo);
//        });
    }

    private void addDataToFirestore() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> data = new HashMap<>();
        data.put("first_name", "Prachi");
        data.put("last_name", "Jamdade");
        database.collection("users")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}