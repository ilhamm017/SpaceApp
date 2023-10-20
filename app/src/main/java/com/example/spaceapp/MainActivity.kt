package com.example.spaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spaceapp.ui.theme.SpaceAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceAppTheme {
                // A surface container using the 'background' color from the theme
                SpaceApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpaceApp() {
    var currentIndex by remember { //Current inden dengan remember yang menyimpan value awal 1
        mutableStateOf(1)
    }
    val listFunction = listOf( //list fungsi yang nantinya akan dipanggil
        LemonadeApp(),
        KocokDaduApp()
    )

    Surface( //container surface yang menjadi pengisi halaman
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column( //format column
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box( //Menggunakan box untuk menampilkan konten
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp)
            ) {
                Surface {
                    when (currentIndex) {
                        1 -> LemonadeApp()
                        2 -> KocokDaduApp()
                    }
                }
            }
            Box( //Menggunakan box untuk menampilkan text judul
                modifier = Modifier
//                    .weight(1f / 4f)
                    .padding(10.dp)
            ) {
                Surface() {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = when (currentIndex) {
                                1 -> stringResource(id = R.string.lemonApp)
                                2 -> stringResource(id = R.string.daduApp)
                                else -> {
                                    "Error"
                                }
                            },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .wrapContentSize()
                        )
                    }
                }
            }
            Box( //Menggunakan box untuk menempatkan navigasi
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1 / 6f)
                    .padding(20.dp)
            ) {
                Surface() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button( //Tombol untuk navigasi mundur
                            onClick = {
                                      if (currentIndex > 1) {
                                          currentIndex --
                                      } else {
                                          currentIndex = listFunction.size
                                      }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Mundur")

                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button( //Tombol untuk navigasi maju
                            onClick = {
                                      if (currentIndex < listFunction.size) {
                                          currentIndex ++
                                      } else {
                                          currentIndex = 1
                                      }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text ="Maju")
                        }
                    }
                }
            }
        }
    }
}


// ===============================================================================

@Composable
fun LemonadeApp() { //fungsi lemonadeApp akan menampilkan game sederhana memeras lemon
    var stepTerkini by remember { //variabel yang menyimpan step terkini
        mutableStateOf(1)
    }
    var perasanLemon by remember { //variabel yang menyimpan jumlah perasan lemon
        mutableStateOf(1)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (stepTerkini) { //operator logika yang menentukan step yang akan ditampilkan
            1 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.pilih_lemon),
                        style = TextStyle(
                            textAlign = TextAlign.Justify
                        )
                        )
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lemon_tree),
                        contentDescription = stringResource(id = R.string.pohon_lemon),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                stepTerkini = 2
                            }
                    )
                }
            }
            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.tap_tap_lemon),
                        style = TextStyle(
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(text = "power "+perasanLemon)
                    Spacer(modifier = Modifier.height(32.dp))
                    Image(
                        painter = painterResource(id = R.drawable.lemon_squeeze),
                        contentDescription = stringResource(id = R.string.lemon),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                perasanLemon += (1..10).random() //Perulangan yang akan menambahkan berapa banyak perasan lemon
                                if (perasanLemon >= 100) { //Jika perasan lemon lebih dari atau sama dengan 100, maka akan lanjut step selanjutnya
                                    stepTerkini = 3
                                }
                            }
                    )
                }
            }
            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.minum_lemon),
                        style = TextStyle(
                            textAlign = TextAlign.Justify
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.lemon_drink),
                        contentDescription = stringResource(id = R.string.gelas_lemon),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                stepTerkini = 4
                                perasanLemon = 1
                            }
                    )
                }
            }
            4 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.mulai_lagi),
                        style = TextStyle(
                            textAlign = TextAlign.Justify
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.lemon_restart),
                        contentDescription = stringResource(id = R.string.gelas_kosong),
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {
                                stepTerkini = 1
                            }
                    )
                }
            }
        }
    }
}

//===================================================================================================

@Composable
fun KocokDaduApp() { //fungsi Kcocok dadu
    KocokDaduDenganGambarDanTombol( //Memanggil fungsi kocokDaduDenganGambardanTombol
        modifier = Modifier     //Menambahkan modifier
            .fillMaxSize()      //Mengisi atau memperluar ukuran maksimum dari komponen
            .wrapContentSize(Alignment.Center) //mengatur ukuran komponen sesuai dengan konten dan memposisikannya di tengah
    )
}

@Composable
fun KocokDaduDenganGambarDanTombol(modifier: Modifier = Modifier) { //fungsi menampilkan dadu dan logika pengocokan
    var result by remember { mutableStateOf(1) } //membuat variabel result yang dihubungkan dengan mutableStateOf. Selain itu karena menggunakan remember, maka perubahan yang terjadi akan tetap diingat dan tetap ada selama siklus hidup komponen yang menggunakan variabel result
    val imageResource = when (result) { //Mendapatkan id dari resources image yang digunakan
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column( //Menampilkan dalam column
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image( //Menampilkan gambar
            painter = painterResource(imageResource),
            contentDescription = result.toString(),
            modifier = Modifier
                .clickable {
                    result = (1..6).random() // mendapatkan nilai result random antara 1 - 6
                }
        )
    }
}