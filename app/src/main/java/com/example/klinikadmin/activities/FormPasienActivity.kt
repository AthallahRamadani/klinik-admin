package com.example.klinikadmin.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.klinikadmin.R
import com.example.klinikadmin.models.PasienModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class FormPasienActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var namaTil: TextInputLayout
    private lateinit var namaEt: EditText
    private lateinit var nikTil: TextInputLayout
    private lateinit var nikEt: EditText
    private lateinit var tanggalTil: TextInputLayout
    private lateinit var tanggalEt: EditText
    private lateinit var kelaminRg: RadioGroup
    private lateinit var priaRb: RadioButton
    private lateinit var wanitaRb: RadioButton
    private lateinit var alamatTil: TextInputLayout
    private lateinit var alamatEt: EditText
    private lateinit var noHpTil: TextInputLayout
    private lateinit var noHpEt: EditText
    private lateinit var simpanBtn: Button


    private lateinit var dbRef: DatabaseReference

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pasien)
        namaTil = findViewById(R.id.til_nama)
        namaEt = findViewById(R.id.et_nama)
        nikTil = findViewById(R.id.til_nik)
        nikEt = findViewById(R.id.et_nik)
        tanggalTil = findViewById(R.id.til_tanggal)
        tanggalEt = findViewById(R.id.et_tanggal)
        kelaminRg = findViewById(R.id.rg_kelamin)
        priaRb = findViewById(R.id.rb_pria)
        wanitaRb = findViewById(R.id.rb_wanita)
        alamatTil = findViewById(R.id.til_alamat)
        alamatEt = findViewById(R.id.et_alamat)
        noHpTil = findViewById(R.id.til_nohp)
        noHpEt = findViewById(R.id.et_nohp)
        simpanBtn = findViewById(R.id.btn_simpan)

        dbRef = FirebaseDatabase.getInstance().getReference("Pasien")

//        tanggalTil.setRawInputType(InputType.TYPE_NULL)
        tanggalEt.setRawInputType(InputType.TYPE_NULL)
        tanggalTil.isFocusable = false
        tanggalEt.isFocusable = false
//        tanggalTil.keyListener = null
        tanggalEt.keyListener = null

        pickDate()

        simpanBtn.setOnClickListener {
            savePasienData()
        }

    }

    private fun savePasienData() {
        //Getting values(isinya saat disimpan)
        val pasienName = namaEt.text.toString()
        val pasienNik = nikEt.text.toString()
        val pasienTanggal = tanggalEt.text.toString()
        val pasienKelamin = when (kelaminRg.checkedRadioButtonId) {
            R.id.rb_pria -> "Pria"
            R.id.rb_wanita -> "Wanita"
            else -> ""
        }
        val pasienAlamat = alamatEt.text.toString()
        val pasienNoHp = noHpEt.text.toString()

        if (!isAllInputValid()){
            Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show()
        } else {
            val pasienId = dbRef.push().key!!
            val pasien = PasienModel(
                pasienId,
                pasienName,
                pasienNik,
                pasienTanggal,
                pasienKelamin,
                pasienAlamat,
                pasienNoHp
            )
            dbRef.child(pasienId).setValue(pasien)
            Toast.makeText(this, "Data berhasil tersimpan", Toast.LENGTH_LONG).show()
            finish()
        }

//        //validasi nama
//        if (pasienName.isEmpty()) {
//            namaTil.setErrorInput("Masukkan nama pasien")
//        } else {
//            namaTil.clearError()
//        }
//
//        //validasi nik
//        if (pasienNik.isEmpty()) {
//            nikTil.setErrorInput("Masukkan NIK")
//        } else {
//            nikTil.clearError()
//        }
//
//        //validasi tanggal lahir
//        if (pasienTanggal.isEmpty()) {
//            tanggalTil.setErrorInput("Masukkan tanggal lahir")
//        } else {
//            tanggalTil.clearError()
//        }
//
//        //validasi alamat
//        if (pasienAlamat.isEmpty()) {
//            alamatTil.setErrorInput("Masukkan alamat")
//        } else {
//            alamatTil.clearError()
//        }
//
//        //validasi no telpon
//        if (pasienNoHp.isEmpty()) {
//            noHpTil.setErrorInput("Masukkan No handphone")
//        } else {
//            noHpTil.clearError()
//        }
//
//
//        val pasienId = dbRef.push().key!!
//
//        val pasien = PasienModel(
//            pasienId,
//            pasienName,
//            pasienNik,
//            pasienTanggal,
//            pasienKelamin,
//            pasienAlamat,
//            pasienNoHp
//        )
//
//        dbRef.child(pasienId).setValue(pasien).addOnCompleteListener {
//            Toast.makeText(this, "Data berhasil tersimpan", Toast.LENGTH_LONG).show()
//            finish()
//        }.addOnFailureListener { err ->
//            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
//        }

    }

    private fun validasiNama():Boolean{
        val pasienName = namaEt.text.toString()

        return if (pasienName.isEmpty()) {
            namaTil.setErrorInput("Masukkan nama pasien")
            false
        } else {
            namaTil.clearError()
            true
        }
    }

    private fun validasiNik():Boolean{
        val pasienNik = nikEt.text.toString()

        return if (pasienNik.isEmpty()) {
            namaTil.setErrorInput("Masukkan NIK pasien")
            false
        } else {
            namaTil.clearError()
            true
        }
    }

    private fun validasiTanggal():Boolean{
        val pasienTanggal = tanggalEt.text.toString()

        return if (pasienTanggal.isEmpty()) {
            namaTil.setErrorInput("Masukkan tanggal lahir pasien")
            false
        } else {
            namaTil.clearError()
            true
        }
    }

    private fun validasiAlamat():Boolean{
        val pasienAlamat = alamatEt.text.toString()

        return if (pasienAlamat.isEmpty()) {
            namaTil.setErrorInput("Masukkan alamat pasien")
            false
        } else {
            namaTil.clearError()
            true
        }
    }

    private fun validasiNoHp():Boolean{
        val pasienNoHp = noHpEt.text.toString()

        return if (pasienNoHp.isEmpty()) {
            namaTil.setErrorInput("Masukkan No HP pasien")
            false
        } else {
            namaTil.clearError()
            true
        }
    }

    private fun isAllInputValid(): Boolean {
        return validasiNama() and
                validasiNik() and
                validasiTanggal() and
                validasiAlamat() and
                validasiNoHp()
    }



    private fun TextInputLayout.setErrorInput(message: String) {
        error = message
        isErrorEnabled = true
    }

    private fun TextInputLayout.clearError() {
        error = null
        isErrorEnabled = false
    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate() {
        tanggalEt.setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        tanggalEt.setText("$savedDay-$savedMonth-$savedYear")
    }

}

