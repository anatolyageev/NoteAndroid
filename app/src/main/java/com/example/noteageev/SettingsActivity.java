package com.example.noteageev;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends AppCompatActivity {
    private ImageButton goBack;
    private RadioGroup radioGroup;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("ThemePref", MODE_PRIVATE);
        int theme = sharedPreferences.getInt("Theme", 1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

        if (theme == 1) {
            MaterialRadioButton b = findViewById(R.id.light_theme);
            b.setChecked(true);
        } else if (theme == 2) {
            MaterialRadioButton b = findViewById(R.id.dark_theme);
            b.setChecked(true);
        }

        goBack = findViewById(R.id.go_back_from_settings);
        radioGroup = findViewById(R.id.radio_group);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.light_theme) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Theme", 1);
                    editor.commit();
                } else if (checkedId == R.id.dark_theme) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("Theme", 2);
                    editor.commit();
                }
            }
        });
    }
}