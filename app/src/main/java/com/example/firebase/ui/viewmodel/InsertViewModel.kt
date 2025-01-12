package com.example.firebase.ui.viewmodel

import com.example.firebase.model.Mahasiswa

fun MahasiswaEvent.toMhsModel() : Mahasiswa = Mahasiswa (
    nim = nim,
    nama = nama,
    jenis_Kelamin = jenis_Kelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)