package com.example.datastorekullanimi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.datastorekullanimi.ui.theme.DataStoreKullanimiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    val context = LocalContext.current
    val aps = AppDataStore(context)
    val sayac = remember {mutableStateOf(0)}


    LaunchedEffect(key1 = true) {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            //Kayıtlar
            aps.kayidAd("Ahmet")
            aps.kayidYas(23)
            aps.kayidBoy(1.23)
            aps.kayitBekarMi(true)
            val liste = HashSet<String>()
            liste.add("Mehmet")
            liste.add("Zeynep")
            aps.kayitArkadasLise(liste)

            //Okuma İşlemleri
            val gelenAd = aps.okuAd()
            val gelenYas = aps.okuYas()
            val gelenBoy = aps.okuBoy()
            val gelenBekarMi = aps.okuBekarMi()
            val gelenListe = aps.okuArkadasListe()

            Log.e("Gelen Ad", gelenAd.toString())
            Log.e("Gelen Yas", gelenYas.toString())
            Log.e("Gelen Boy", gelenBoy.toString())
            Log.e("Gelen Bekar Mı", gelenBekarMi.toString())
            Log.e("Gelen Liste", gelenListe.toString())

            //Sayac
            var gelenSayac = aps.okuSayac()
            sayac.value = ++gelenSayac
            aps.kayidSayac(gelenSayac)
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Açılış Sayfa : ${sayac.value}", fontSize = 36.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataStoreKullanimiTheme {
        Sayfa()
    }
}