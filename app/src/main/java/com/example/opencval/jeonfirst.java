package com.example.opencval;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class jeonfirst extends AppCompatActivity {
    Button hansikmenu;
    Button jeonreview;
    Button jeonstart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kimchijeonfirst);


        hansikmenu = (Button) findViewById(R.id.button_hanbefore);
        hansikmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jeonfirst.this,hansik.class);
                startActivity(intent);
            }
        });

        jeonstart = (Button) findViewById(R.id.button_jeonyoristart);
        jeonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jeonfirst.this,jeoncook1.class);
                startActivity(intent);
            }
        });



    }
}
