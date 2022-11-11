package com.example.ruangan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etKodeRuang2, etKapasitas2;
    Button btnUpdate, btnHapus;

    String id, kodeRuang, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etKodeRuang2 = findViewById(R.id.etKodeRuang2);
        etKapasitas2 = findViewById(R.id.etKapasitas2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnHapus = findViewById(R.id.btnHapus);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle(kodeRuang);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(UpdateActivity.this);
                kodeRuang = etKodeRuang2.getText().toString().trim();
                kapasitas = etKapasitas2.getText().toString().trim();
                db.updatedata(id, kodeRuang, kapasitas);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("kodeRuang") && getIntent().hasExtra("kapasitas")){
            id = getIntent().getStringExtra("id");
            kodeRuang = getIntent().getStringExtra("kodeRuang");
            kapasitas = getIntent().getStringExtra("kapasitas");

            etKapasitas2.setText(kodeRuang);
            etKapasitas2.setText(kapasitas);
            Log.d("stev", kodeRuang+" "+kapasitas);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus " + kodeRuang + " ?");
        builder.setMessage("Ingein menghapus " + kodeRuang + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHandler db = new DatabaseHandler(UpdateActivity.this);
                db.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}