package com.example.donacare.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.donacare.Preferences;
import com.example.donacare.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class InputDonasiActivity extends AppCompatActivity {
    Button btn_inputFoto,btn_kirim;
    EditText etJenis, etJumlah, etBerat, etAlamat;
    ProgressDialog progressDialog;
    Preferences preferences;

    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference fileReference;

    final int IMG_REQUEST = 1000;
    Uri imageUri, downloadUri;
    String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_donasi);


        final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, permissions)) {
            EasyPermissions.requestPermissions(this, "Grant the permission to fully access this apps", 10, permissions);
        }

        btn_inputFoto = findViewById(R.id.btn_inputFoto_inputDonasi);
        btn_kirim = findViewById(R.id.btn_Kirim);
        etJenis = findViewById(R.id.et_Jenis);
        etJumlah = findViewById(R.id.et_Jumlah);
        etBerat = findViewById(R.id.et_Berat);
        etAlamat = findViewById(R.id.et_Alamat);

        preferences = new Preferences();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Items");

        progressDialog = new ProgressDialog(this);

        String jenis = etJenis.getText().toString();
        String jumlah = etJumlah.getText().toString();
        String berat = etBerat.getText().toString();
        String alamat = etAlamat.getText().toString();



        btn_inputFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jenis.trim().isEmpty()) {
                    etJenis.setError("Kolom tidak boleh kosong!");
                } else if(jumlah.trim().isEmpty()) {
                    etJumlah.setError("kolom tidak boleh kosong!");
                }else if(berat.trim().isEmpty()){
                    etBerat.setError("kolom tidak boleh kosong!");
                }else if(alamat.trim().isEmpty()){
                    etAlamat.setError("kolom tidak boleh kosong!");
                }else{
                    progressDialog.setMessage("Sending your data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    startActivity(new Intent(InputDonasiActivity.this,HomeActivity.class));
                }
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            imageUri = data.getData();
//            imageUrl = data.getDataString();
////            Glide.with(getApplicationContext()).load(imageUri).fitCenter().into(img_profile);
//        }
//    }


    // Request Permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
//
//    @Override
//    public void onPermissionsGranted (int requestCode, @NonNull List<String> perms) {
//        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
//    }
//
//
//
//    @Override
//    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this).build().show();
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}