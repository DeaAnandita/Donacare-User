package com.example.donacare.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donacare.R;

public class InputDonasiDanaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dana);

        Toolbar toolbar = findViewById(R.id.toolbar_input_donasi_dana);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donasi Jasa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}