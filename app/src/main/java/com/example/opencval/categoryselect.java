package com.example.opencval;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class categoryselect extends AppCompatActivity {
    Button hansik;
    Button jungsik;
    Button yangsik;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoryselect);


        hansik = (Button) findViewById(R.id.button_hansik);
        hansik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categoryselect.this,hansik.class);
                startActivity(intent);
            }
        });



    }
}
