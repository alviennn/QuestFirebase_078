package com.example.firebase.repository

import com.example.firebase.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

interface MahasiswaRepository{
    fun getMahasiswa(): Flow<List<Mahasiswa>>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)

    fun getMahasiswaById(nim: String): Flow<Mahasiswa?>
}

class NetworkRepositoryMhs(
    private val firestore: FirebaseFirestore
) : MahasiswaRepository {
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        try {
            firestore.collection("Mahasiswa").add(mahasiswa).await()
        } catch (e: Exception) {
            throw Exception("Gagal menambahkan data Mahasiswa: ${e.message}")
        }
    }

    override fun getMahasiswa(): Flow<List<Mahasiswa>> = callbackFlow {
        val mhsCollection = firestore.collection("Mahasiswa")
            .orderBy("nim", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    val mhsList = value.documents.mapNotNull {
                        it.toObject(Mahasiswa::class.java)!!
                    }
                    trySend(mhsList) // try send memberikan fungsi untuk mengirim data ke flow
                }
            }
        awaitClose {
            mhsCollection.remove()
        }
    }

    override fun getMahasiswaById(nim: String): Flow<Mahasiswa?> = callbackFlow {
        val mhsCollection = firestore.collection("Mahasiswa")
            .document(nim)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    val mhs = value.toObject(Mahasiswa::class.java)
                    trySend(mhs)
                }
            }
        awaitClose {
            mhsCollection.remove()
        }
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            firestore.collection("Mahasiswa")
                .document(nim)
                .delete()
                .await()
        } catch (e: Exception) {
            throw Exception("Gagal menghapus data Mahasiswa: ${e.message}")
        }
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa){
        try {
            firestore.collection("Mahasiswa")
                .document(nim)
                .set(mahasiswa)
                .await()
        } catch (e: Exception) {
            throw Exception("Gagal mengupdate data Mahasiswa: ${e.message}")
        }
    }
}

