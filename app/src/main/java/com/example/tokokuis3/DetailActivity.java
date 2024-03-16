package com.example.tokokuis3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvDetailNama = findViewById(R.id.tvDetailNama);
        TextView tvDetailMembership = findViewById(R.id.tvDetailMembership);
        TextView tvKodeBarang = findViewById(R.id.tvKodeBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        TextView tvDiskon = findViewById(R.id.tvDiskon);
        TextView tvDiskonMember = findViewById(R.id.tvDiskonMember);
        TextView tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        Button btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        String namaPembeli = intent.getStringExtra("namaPembeli");
        String tipeMember = intent.getStringExtra("tipeMember");
        String kodeBarang = intent.getStringExtra("kodeBarang");
        int jumlahBarang = intent.getIntExtra("jumlahBarang", 0);

        double hargaBarang = 0;
        double totalHarga = 0;
        double diskon = 0;
        double diskonMember = 0;
        double jumlahBayar = 0;

        if (kodeBarang != null) {
            switch (kodeBarang) {
                case "IPX":
                    hargaBarang = 5725300;
                    break;
                case "O17":
                    hargaBarang = 2500999;
                    break;
                case "MP3":
                    hargaBarang = 28999999;
                    break;
            }

            if (!kodeBarang.equals("IPX") && !kodeBarang.equals("O17") && !kodeBarang.equals("MP3")) {
                Toast.makeText(this, "Kode Barang tidak Valid", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }

        totalHarga = hargaBarang * jumlahBarang;


        if (totalHarga > 10000000) {
            diskon = 100000;
        }

        if (tipeMember != null) {
            switch (tipeMember) {
                case "Gold":
                    diskonMember = 0.1 * totalHarga;
                    break;
                case "Silver":
                    diskonMember = 0.05 * totalHarga;
                    break;
                case "Bronze":
                    diskonMember = 0.02 * totalHarga;
                    break;
            }
        }

        jumlahBayar = totalHarga - diskon - diskonMember;

        tvDetailNama.setText("Nama Pembeli : " + namaPembeli);
        tvDetailMembership.setText("Membership Pembeli : " + tipeMember);
        tvKodeBarang.setText("Kode Barang : " + kodeBarang);
        tvNamaBarang.setText("Nama Barang : " + getNamaBarang(kodeBarang));
        tvHarga.setText("Harga : Rp " + String.format("%,.0f", hargaBarang));
        tvTotalHarga.setText("Total Harga : Rp " + String.format("%,.0f", totalHarga));
        tvDiskon.setText("Diskon: Rp " + String.format("%,.0f", diskon));
        tvDiskonMember.setText("Diskon Member : Rp " + String.format("%,.0f", diskonMember));
        tvJumlahBayar.setText("Jumlah Bayar : Rp " + String.format("%,.0f", jumlahBayar));

        final String finalKodeBarang = kodeBarang;
        final double finalTotalHarga = totalHarga;

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Nama Barang : " + getNamaBarang(finalKodeBarang) + "\nMelakukan Transaksi Sebesar Rp " + String.format("%,.0f", finalTotalHarga) + " Pada Dip Tech Store");
                startActivity(Intent.createChooser(intent, "Bagikan melalui"));
            }
        });
    }
    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "IPX":
                return "Iphone X";
            case "O17":
                return "Oppo A17";
            case "MP3":
                return "Macbook Pro M3";
            default:
                return "";
        }
    }
}
