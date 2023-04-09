package com.oneconstant.safechat.screens.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oneconstant.safechat.R;
import com.oneconstant.safechat.aes.EncryptionUtil;
import com.oneconstant.safechat.screens.Main;

public class Chat extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String textForEncryption = "This my text that will be encrypted!";
        String encryptedString = EncryptionUtil.encrypt(textForEncryption);
        //Result: BItjtWPNKXjdHZ6clbtXWAzUwJAbMpIaP294eRB9+7BR0g+gJ9jZv4AZt+8epG36
        Log.d("AES", encryptedString);
        String encryptedText = "BItjtWPNKXjdHZ6clbtXWAzUwJAbMpIaP294eRB9+7BR0g+gJ9jZv4AZt+8epG36";
        String decryptedString = EncryptionUtil.decrypt(encryptedText);
        Log.d("AES", decryptedString);
        //Result: This my text that will be encrypted!

        return inflater.inflate(R.layout.fragment_chat, container, false);
    }


}