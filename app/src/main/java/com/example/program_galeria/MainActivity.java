package com.example.program_galeria;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private int[] images = { R.drawable.fox, R.drawable.wilk, R.drawable.ghost, R.drawable.ptak };
    private int currentIndex = 0;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgPhoto = findViewById(R.id.imgPhoto);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnPrev = findViewById(R.id.btnPrev);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        Switch switchBlue = findViewById(R.id.switchBlue);

        showImage();

        btnNext.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex >= images.length) currentIndex = 0;
            showImage();
        });

        btnPrev.setOnClickListener(v -> {
            currentIndex--;
            if (currentIndex < 0) currentIndex = images.length - 1;
            showImage();
        });

        switchBlue.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mainLayout.setBackgroundColor(Color.parseColor("#1565c0"));
            } else {
                mainLayout.setBackgroundColor(Color.parseColor("#00796B"));
            }
        });
        EditText edtNumber = findViewById(R.id.edtNumber);

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              try{
                  int value = Integer.parseInt(s.toString());
                  if (value >= 1 && value <= images.length){
                      currentIndex = value - 1;
                      showImage();
                  }
              }catch (NumberFormatException e){

              }
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showImage() {
        imgPhoto.setImageResource(images[currentIndex]);
    }
}

