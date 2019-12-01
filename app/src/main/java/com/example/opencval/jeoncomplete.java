package com.example.opencval;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class jeoncomplete extends AppCompatActivity {
    Button yorirodol;
    Button reviewmake;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeoncomplete);


        yorirodol = (Button) findViewById(R.id.button_yoridol);
        yorirodol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jeoncomplete.this,jeonfirst.class);
                startActivity(intent);
            }
        });

        reviewmake = (Button) findViewById(R.id.button_reviewmake);
        yorirodol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jeoncomplete.this,categoryselect.class);
                startActivity(intent);
            }
        });




    }
}
