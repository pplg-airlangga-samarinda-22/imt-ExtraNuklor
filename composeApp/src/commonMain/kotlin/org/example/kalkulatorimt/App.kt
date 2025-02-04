package org.example.kalkulatorimt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kalkulator_imt.composeapp.generated.resources.Res
import kalkulator_imt.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    var beratBadan by remember { mutableStateOf("0") }
    var tinggiBadan by remember { mutableStateOf("0") }
    var hasil  by remember { mutableStateOf(0.0f) }
    var kategori by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Kalkulator IMT", fontWeight = FontWeight.Bold, fontSize = 24.sp) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null
                    )
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        content = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Text(text = "Berat Badan :")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    TextField(
                                        value = beratBadan,
                                        onValueChange = { beratBadan = it },
                                        singleLine = true,
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "kg", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Text(text = "Tinggi Badan :")
                                    Spacer(modifier = Modifier.width(10.dp))
                                    TextField(
                                        value = tinggiBadan,
                                        onValueChange = { tinggiBadan = it },
                                        singleLine = true,
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "Meter", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if (beratBadan.isNotBlank() && tinggiBadan.isNotBlank()) {
                                hasil = beratBadan.toFloat() / (tinggiBadan.toFloat() * tinggiBadan.toFloat())
                            } else {
                                kategori = "Tolong isi Dengan Benar!"
                            }
                            when {
                               hasil < 17.0 -> kategori = "Kurus, Kekurangan Berat Badan Berat"
                                hasil in 17.0..18.4 -> kategori = "Kurus,Kekurangan Berat Badan Ringan"
                                hasil in 18.5..25.0 -> kategori = "Normal"
                                hasil in 25.1..27.0 -> kategori = "Gemuk, Kelebihan Berat Badan tingkat ringan"
                                hasil >= 27.0 -> kategori = "Gemuk, Kelebihan Berat Badan tingkat berat"
                            }
                        },
                        content = { Text(text = "Lihat IMT") }
                    )
                    Text(text = "$hasil,Kategori: $kategori")
                }
            )
        }
    )
}