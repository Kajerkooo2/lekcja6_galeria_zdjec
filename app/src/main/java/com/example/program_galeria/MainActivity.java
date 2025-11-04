package com.example.program_galeria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int[] images = { R.drawable.fox, R.drawable.ghost, R.drawable.ptak, R.drawable.wilk };
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
    }

    private void showImage() {
        imgPhoto.setImageResource(images[currentIndex]);
    }
}