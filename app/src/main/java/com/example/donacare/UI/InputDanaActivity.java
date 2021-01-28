package com.example.donacare.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.donacare.R;

public class InputDanaActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btnKirim;
    EditText nominal,noRek,atasNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dana);

        btnKirim = findViewById(R.id.btn_Kirim_Dana);
        nominal= findViewById(R.id.et_nominal_inputDana);
        noRek=findViewById(R.id.et_noRekening_inputDana);
        atasNama=findViewById(R.id.et_atasNama_inputDana);

        String nom = nominal.getText().toString();
        String no = noRek.getText().toString();
        String atNam = atasNama.getText().toString();

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nom.trim().isEmpty()) {
                    nominal.setError("Kolom tidak boleh kosong!");
                } else if(no.trim().isEmpty()) {
                    noRek.setError("kolom tidak boleh kosong!");
                }else if(atNam.trim().isEmpty()){
                    atasNama.setError("kolom tidak boleh kosong!");
                }else{
                    progressDialog.setMessage("Sending your data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    startActivity(new Intent(InputDanaActivity.this,HomeActivity.class));
                }
            }
        });
    }
}