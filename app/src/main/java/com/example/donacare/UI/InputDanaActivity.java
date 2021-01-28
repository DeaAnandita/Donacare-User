package com.example.donacare.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donacare.Model.AccountModel;
import com.example.donacare.Preferences;
import com.example.donacare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputDanaActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btnKirim;
    EditText Nominal, NoRek, AtasNama;

    DatabaseReference databaseReference;
    Preferences preferences;
    String email, name, address, phone;

    Spinner spItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dana);



        preferences = new Preferences();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        progressDialog = new ProgressDialog(this);

        btnKirim = findViewById(R.id.btn_Kirim_Dana);
        Nominal = findViewById(R.id.et_nominal_inputDana);
        NoRek = findViewById(R.id.et_noRekening_inputDana);
        AtasNama = findViewById(R.id.et_atasNama_inputDana);
        spItem = findViewById(R.id.spPilihanDonasi);

        String nominal = Nominal.getText().toString();
        String noRek = NoRek.getText().toString();
        String atName = AtasNama.getText().toString();

        String[] ITEMS = {"Barang", "Dana", "Jasa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItem.setAdapter(adapter);

        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Intent intent = null;
                if (item.equals("Barang")) {
                    intent = new Intent(getApplicationContext(), InputDonasiActivity.class);
                } else if (item.equals("Jasa")) {
//                    intent = new Intent(getApplicationContext(), Input)
                }
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nominal.trim().isEmpty()) {
                    Nominal.setError("Kolom tidak boleh kosong!");
                } else if (noRek.trim().isEmpty()) {
                    NoRek.setError("kolom tidak boleh kosong!");
                } else if (atName.trim().isEmpty()) {
                    AtasNama.setError("kolom tidak boleh kosong!");
                } else {
                    progressDialog.setMessage("Sending your data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Nominal.setError(null);
                    NoRek.setError(null);
                    AtasNama.setError(null);

                    databaseReference.child(atName);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            progressDialog.dismiss();
                            if (snapshot.getValue() != null) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                }
                                if (true) {
                                    preferences.setName(getApplicationContext(), name);
                                    preferences.setAddress(getApplicationContext(), address);
//                                    preferences.setUsername(getApplicationContext(), username);
                                    preferences.setEmail(getApplicationContext(), email);
                                    preferences.setPhone(getApplicationContext(), phone);
                                    preferences.setStatus(getApplicationContext(), true);

                                    Log.d("email", email);
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Something is wrong..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();
                        }
                    });
                    startActivity(new Intent(InputDanaActivity.this, HomeActivity.class));
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbarInput);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donasi Jasa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}