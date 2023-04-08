package com.oneconstant.safechat.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.oneconstant.safechat.R;
import com.oneconstant.safechat.databinding.ActivityVerifyOtpBinding;
import com.oneconstant.safechat.screens.Main;

public class VerifyOTP extends AppCompatActivity {

    private ActivityVerifyOtpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.nextBtn.setOnClickListener(v -> {
            if (mBinding.input1.getText().toString() != "" && mBinding.input2.getText().toString() != "" && mBinding.input3.getText().toString() != "" && mBinding.input4.getText().toString() != "" && mBinding.input5.getText().toString() != "" && mBinding.input6.getText().toString() != "") {
                startActivity(new Intent(VerifyOTP.this, Main.class));
                finish();
            }
            else {
                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}