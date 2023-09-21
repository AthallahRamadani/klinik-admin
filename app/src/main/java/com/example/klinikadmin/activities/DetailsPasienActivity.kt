package com.example.klinikadmin.activities

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.klinikadmin.R

class DetailsPasienActivity : AppCompatActivity() {

    private lateinit var tvPasienId : TextView
    private lateinit var tvPasienNama : TextView
    private lateinit var tvPasienNik : TextView
    private lateinit var tvPasienTanggal : TextView
    private lateinit var tvPasienJenisKelamin: TextView
    private lateinit var tvPasienAlamat : TextView
    private lateinit var tvPasienNoHp: TextView
    private lateinit var btnPerbarui: Button
    private lateinit var btnHapus : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_pasien)

        initView()
        setValuesToViews()
    }


    private fun setValuesToViews() {
        tvPasienId.text = intent.getStringExtra("pasienID")
        tvPasienNama.text = intent.getStringExtra("pasienName")
        tvPasienNik.text = intent.getStringExtra("pasienNik")
        tvPasienTanggal.text = intent.getStringExtra("pasienTanggal")
        tvPasienJenisKelamin.text = intent.getStringExtra("pasienKelamin")
        tvPasienAlamat.text = intent.getStringExtra("pasienAlamat")
        tvPasienNoHp.text = intent.getStringExtra("pasienNoHp")

    }

    private fun initView() {
        tvPasienId= findViewById(R.id.tv_id)
        tvPasienNama = findViewById(R.id.tv_nama)
        tvPasienNik = findViewById(R.id.tv_nik)
        tvPasienTanggal = findViewById(R.id.tv_tanggal)
        tvPasienJenisKelamin = findViewById(R.id.tv_kelamin)
        tvPasienAlamat = findViewById(R.id.tv_alamat)
        tvPasienNoHp = findViewById(R.id.tv_nohp)

        btnHapus = findViewById(R.id.btn_hapus)
        btnPerbarui = findViewById(R.id.btn_perbarui)

    }
}