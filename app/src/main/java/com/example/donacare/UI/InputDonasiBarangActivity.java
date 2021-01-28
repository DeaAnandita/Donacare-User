package com.example.donacare.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.donacare.R;

public class InputDonasiBarangActivity extends AppCompatActivity {
    Button btn_inputFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_donasi_barang);
        btn_inputFoto= findViewById(R.id.btn_inputFoto_inputDonasi);
        btn_inputFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}