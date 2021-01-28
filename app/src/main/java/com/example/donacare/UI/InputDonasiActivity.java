package com.example.donacare.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.donacare.Preferences;
import com.example.donacare.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class InputDonasiActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    String id, media;
    Spinner spItem;
    Button btn_inputFoto,btn_kirim;
    EditText txtJenisBarang, txtJumlahBarang, txtBeratBarang, txtAlamatLengkap;
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

        spItem = findViewById(R.id.spPilihanDonasi);
        btn_inputFoto = findViewById(R.id.btn_inputFoto_inputDonasi);
        btn_kirim = findViewById(R.id.btnKirim);
        txtJenisBarang = findViewById(R.id.txtJenisBarang);
        txtJumlahBarang = findViewById(R.id.txtJumlahBarang);
        txtBeratBarang = findViewById(R.id.txtBeratBarang);
        txtAlamatLengkap = findViewById(R.id.txtAlamatLengkap);

        preferences = new Preferences();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Items");

        progressDialog = new ProgressDialog(this);

        String jenis = txtJenisBarang.getText().toString();
        String jumlah = txtJumlahBarang.getText().toString();
        String berat = txtBeratBarang.getText().toString();
        String alamat = txtAlamatLengkap.getText().toString();
        
        btn_inputFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        String[] ITEMS = {"Barang", "Dana", "Jasa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItem.setAdapter(adapter);

        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Intent intent = null;
                if (item.equals("Dana")) {
                    intent = new Intent(getApplicationContext(), InputDanaActivity.class);
                } else if (item.equals("Jasa")) {
                    intent = new Intent(getApplicationContext(), InputDjasaActivity.class);
                }
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jenis.trim().isEmpty() ||jumlah.trim().isEmpty()||berat.trim().isEmpty()||alamat.trim().isEmpty()) {
                    Toast.makeText(InputDonasiActivity.this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.setMessage("Sending your data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    fileReference = storageReference.child(media).child(id).child("profile." + getFileExtension(imageUri));
                    fileReference.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return fileReference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                downloadUri = task.getResult();
                                imageUrl = downloadUri.toString();
                                startActivity(new Intent(InputDonasiActivity.this,HomeActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Edit failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    // Request Permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted (int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

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