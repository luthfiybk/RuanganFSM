 package com.example.ruangan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


 public class AddActivity extends AppCompatActivity {

    EditText kodeRuang, kapasitas;
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        kodeRuang = findViewById(R.id.etKodeRuang);
        kapasitas = findViewById(R.id.etKapasitas);
        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(AddActivity.this);
                db.addRuang(kodeRuang.getText().toString().trim(),
                        kapasitas.getText().toString().trim());
            }
        });
    }
}