package com.fauzan.mysunmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fauzan.sunmilib.Alignment;
import com.fauzan.sunmilib.EzSunmi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EzSunmi.getInstance(this);
        Button btnPrint = findViewById(R.id.btn_print);
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EzSunmi.setAlignment(Alignment.CENTER);
                EzSunmi.printLogo(R.drawable.m1paywallet_print);
                EzSunmi.addLine(3);
            }
        });
    }
}
