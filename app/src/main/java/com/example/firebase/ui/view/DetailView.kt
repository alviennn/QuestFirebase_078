package com.example.firebase.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase.ui.viewmodel.DetailUiState
import com.example.firebase.ui.viewmodel.DetailViewModel
import com.example.firebase.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.mahasiswaDetailState

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Detail Mahasiswa") },
                navigationIcon = {
                    Button(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (uiState) {
                    is DetailUiState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                    is DetailUiState.Error -> Text(
                        text = "Gagal memuat data.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    is DetailUiState.Success -> {
                        val mahasiswa = uiState.mahasiswa
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Card displaying Mahasiswa information
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(text = "NIM: ${mahasiswa.nim}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Nama: ${mahasiswa.nama}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Alamat: ${mahasiswa.alamat}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Jenis Kelamin: ${mahasiswa.jenis_kelamin}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Kelas: ${mahasiswa.kelas}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Angkatan: ${mahasiswa.angkatan}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Judul Skripsi: ${mahasiswa.judul_skripsi}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Dosen Pembimbing 1: ${mahasiswa.dosen_pembimbing}", style = MaterialTheme.typography.bodyLarge)
                                    Text(text = "Dosen Pembimbing 2: ${mahasiswa.dosen_pembimbing_2}", style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}