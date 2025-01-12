package com.example.firebase.ui.viewmodel

import com.example.firebase.model.Mahasiswa

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenis_Kelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
)

//data class Variabel yang menyimpan data input form
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenis_Kelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

//Menyimpan input form ke dalam entity
fun MahasiswaEvent.toMhsModel() : Mahasiswa = Mahasiswa (
    nim = nim,
    nama = nama,
    jenis_Kelamin = jenis_Kelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)