package com.oneconstant.safechat.screens.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oneconstant.safechat.R;
import com.oneconstant.safechat.databinding.FragmentTranslateBinding;

import java.util.Objects;

public class Translate extends Fragment {

    private FragmentTranslateBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentTranslateBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        mBinding.mic.setOnClickListener(v -> {
            speak();
        });
        return mBinding.getRoot();
    }

    public void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start Speaking");
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK) {
            mBinding.text.setText(Objects.requireNonNull(data).getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0).toString());
        }
    }
}