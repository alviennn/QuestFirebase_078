package com.example.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.model.Mahasiswa
import com.example.firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    data class Error(val exception: Throwable) : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
) : ViewModel() {

    var mahasiswaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    private val _nim: String = checkNotNull(savedStateHandle["NIM"])

    init {
        getMahasiswabyNim()
    }

    fun getMahasiswabyNim() {
        viewModelScope.launch {
            mhs.getMahasiswaById(_nim)
                .onStart {
                    mahasiswaDetailState = DetailUiState.Loading
                }
                .catch { exception ->
                    mahasiswaDetailState = DetailUiState.Error(exception)
                }
                .collect { mahasiswa ->
                    mahasiswaDetailState = if (mahasiswa != null) {
                        DetailUiState.Success(mahasiswa)
                    } else {
                        DetailUiState.Error(Exception("Mahasiswa dengan NIM $_nim tidak ditemukan"))
                    }
                }
        }
    }
}
