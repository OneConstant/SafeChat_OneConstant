package com.oneconstant.safechat.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.navigation.NavigationBarView;
import com.oneconstant.safechat.R;
import com.oneconstant.safechat.databinding.ActivityLoginBinding;
import com.oneconstant.safechat.databinding.ActivityMainBinding;
import com.oneconstant.safechat.screens.fragments.Chat;
import com.oneconstant.safechat.screens.fragments.Home;
import com.oneconstant.safechat.screens.fragments.Profile;
import com.oneconstant.safechat.screens.fragments.Translate;

public class Main extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Translate()).commit();
        BadgeDrawable badgeDrawable = mBinding.bottomNavigation.getOrCreateBadge(R.id.chat);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);
        badgeDrawable.setBackgroundColor(Color.parseColor("#FFDD65"));
        mBinding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.translate) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Translate()).commit();
                    getSupportFragmentManager().popBackStack("translate", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getSupportFragmentManager().beginTransaction().addToBackStack("translate").commit();
                    return true;
                }
                else if(item.getItemId() == R.id.chat) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Chat())
                            .addToBackStack("chat").commit();
                    return true;
                }
                return false;
            }
        });
    }
}