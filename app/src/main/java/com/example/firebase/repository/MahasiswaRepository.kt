package com.example.firebase.repository

import com.example.firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow


interface MahasiswaRepository{
    fun getMahasiswa(): Flow<List<Mahasiswa>>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    fun getMahasiswaById(nim: String): Flow<Mahasiswa?>
}


