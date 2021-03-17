package com.divesh.picseditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.divesh.picseditor.databinding.ActivityMainBinding;
import com.divesh.picseditor.databinding.ActivitySaveResultBinding;

public class SaveResultActivity extends AppCompatActivity {

    ActivitySaveResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Uri imageUri = getIntent().getData();
        if (imageUri != null){
            binding.resultImage.setImageURI(imageUri);
            Toast.makeText(this,"Saved To Gallery",Toast.LENGTH_LONG).show();
        }


        binding.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                startActivity(Intent.createChooser(shareIntent,"Share Image Via"));
            }
        });

        binding.instaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifyAppInstallation("com.instagram.android")){
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setPackage("com.instagram.android");

                    startActivity(shareIntent);
                }else {
                    Toast.makeText(SaveResultActivity.this,"Instagram is not installed in your phone",Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.driveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyAppInstallation("com.google.android.apps.docs")) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.google.android.apps.docs");

                    startActivity(shareIntent);
                }else {
                    Toast.makeText(SaveResultActivity.this,"Drive is not installed in your phone",Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.facebookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifyAppInstallation("com.facebook.katana")) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setPackage("com.facebook.katana");

                    startActivity(shareIntent);
                }else {
                    Toast.makeText(SaveResultActivity.this,"Facebook is not installed in your phone",Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private boolean verifyAppInstallation(String packagename){
        boolean installed = false;

        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(packagename, 0);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}