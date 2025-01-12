package com.example.firebase.ui.viewmodel

import com.example.firebase.model.Mahasiswa

sealed class FormState{
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}

data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenis_Kelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null
){
    fun isValid(): Boolean {
        return nim != null && nama != null && jenis_Kelamin != null &&
                alamat != null && kelas != null && angkatan != null
    }
}

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
    jenis_kelamin = jenis_Kelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)