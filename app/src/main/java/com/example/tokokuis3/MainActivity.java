package com.example.tokokuis3;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etNamaPembeli = findViewById(R.id.etNamaPembeli);
        EditText etKodeBarang = findViewById(R.id.etKodeBarang);
        EditText etJumlahBarang = findViewById(R.id.etJumlahBarang);
        etJumlahBarang.setInputType(InputType.TYPE_CLASS_NUMBER);
        RadioGroup rbMember = findViewById(R.id.rbMember);
        Button btnProses = findViewById(R.id.btnProses);
        Button btnReset = findViewById(R.id.btnReset);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaPembeli = etNamaPembeli.getText().toString();
                int selectedId = rbMember.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedId);
                String tipeMember = rbSelected != null ? rbSelected.getText().toString() : "";
                String kodeBarang = etKodeBarang.getText().toString().toUpperCase();
                String jumlahBarangStr = etJumlahBarang.getText().toString();

                if (namaPembeli.isEmpty() || tipeMember.isEmpty() || kodeBarang.isEmpty() || jumlahBarangStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Harap lengkapi semua input", Toast.LENGTH_SHORT).show();
                    return;
                }

                int jumlahBarang = Integer.parseInt(jumlahBarangStr);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("namaPembeli", namaPembeli);
                intent.putExtra("tipeMember", tipeMember);
                intent.putExtra("kodeBarang", kodeBarang);
                intent.putExtra("jumlahBarang", jumlahBarang);

                startActivity(intent);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNamaPembeli.setText("");
                etKodeBarang.setText("");
                etJumlahBarang.setText("");
                rbMember.clearCheck();
            }
        });
    }
}
