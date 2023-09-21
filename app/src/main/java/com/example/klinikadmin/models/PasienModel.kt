package com.example.klinikadmin.models

data class PasienModel(
    var pasienId: String? = null,
    var pasienName: String?=null,
    var pasienNik: String?=null,
    var pasienTanggal: String?=null,
    var pasienKelamin: String?=null,
    var pasienAlamat: String?=null,
    var pasienNoHp: String?=null

    )