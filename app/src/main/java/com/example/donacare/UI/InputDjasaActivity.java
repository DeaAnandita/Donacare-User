package com.example.donacare.UI;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donacare.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

public class InputDjasaActivity extends AppCompatActivity implements IPickResult {

    ImageView img_CV, img_porto, img_pasfoto;

    private Bitmap selectedImage;

    private String selectedImagePathCV = "";
    private String selectedImagePathPorto = "";
    private String selectedImagePathPasfoto = "";

    String foto_CV = "";
    String foto_porto = "";
    String foto_pasfoto = "";

    String sjenis;
    private File fileselectedImagePathCV;
    private File fileselectedImagePathPorto;
    private File fileselectedImagePathPasfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_djasa);

        Toolbar toolbar = findViewById(R.id.toolbarInput_DJasa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Donasi Jasa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_CV = findViewById(R.id.img_CV);
        img_porto = findViewById(R.id.img_porto);
        img_pasfoto = findViewById(R.id.img_pasfoto);

        img_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto_CV = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        img_porto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto_porto = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
        img_pasfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto_pasfoto = "true";
                PickImageDialog.build(new PickSetup()).show(getSupportFragmentManager());
            }
        });
    }

        @Override
        public void onPickResult(PickResult r) {
        if (r.getError() == null & foto_CV.equalsIgnoreCase("true")) {

            selectedImagePathCV = r.getPath().toString();
            Log.d("test", "onPickResult: " + selectedImagePathCV);

            selectedImage = r.getBitmap();
            img_CV.setImageBitmap(selectedImage);
            foto_CV = "";

        }else if(r.getError() == null & foto_porto.equalsIgnoreCase("true")) {
                selectedImagePathPorto = r.getPath().toString();
                Log.d("test", "onPickResult: " + selectedImagePathPorto);

                selectedImage = r.getBitmap();
                img_porto.setImageBitmap(selectedImage);
                foto_porto = "";

        }else if(r.getError() == null & foto_pasfoto.equalsIgnoreCase("true")) {
                selectedImagePathPorto = r.getPath().toString();
                Log.d("test", "onPickResult: " + selectedImagePathPasfoto);

                selectedImage = r.getBitmap();
                img_pasfoto.setImageBitmap(selectedImage);
                foto_pasfoto = "";
    }
}}