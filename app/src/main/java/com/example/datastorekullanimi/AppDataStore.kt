package com.example.datastorekullanimi

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppDataStore(var context:Context) {

    val Context.ds : DataStore<Preferences> by preferencesDataStore("bilgiler")
    companion object {
        val AD_KEY = stringPreferencesKey("AD")
        val YAS_KEY = intPreferencesKey("YAS")
        val BOY_KEY = doublePreferencesKey("BOY")
        val BEKAR_MI_KEY = booleanPreferencesKey("BEKAR_MI")
        val ARKADAS_LISTE_KEY = stringSetPreferencesKey("ARKADAS_LISTE")
        val SAYAC_KEY = intPreferencesKey("SAYAC")
    }
    suspend fun kayidAd(ad:String){
        context.ds.edit {
            it[AD_KEY] = ad
        }
    }
    suspend fun okuAd():String{
        val p = context.ds.data.first()
        return p[AD_KEY]?:"İsim Yok"
    }
    suspend fun silAd(){
        context.ds.edit {
            it.remove(AD_KEY)
        }
    }

    suspend fun kayidYas(yas:Int){
        context.ds.edit {
            it[YAS_KEY] = yas
        }
    }
    suspend fun okuYas():Int{
        val p = context.ds.data.first()
        return p[YAS_KEY]?:0
    }

    suspend fun kayidBoy(boy:Double){
        context.ds.edit {
            it[BOY_KEY] = boy
        }
    }
    suspend fun okuBoy():Double{
        val p = context.ds.data.first()
        return p[BOY_KEY]?:0.00
    }

    suspend fun kayitBekarMi(bekarMi:Boolean){
        context.ds.edit {
            it[BEKAR_MI_KEY] = bekarMi
        }
    }
    suspend fun okuBekarMi():Boolean{
        val p = context.ds.data.first()
        return p[BEKAR_MI_KEY]?:false
    }
    suspend fun kayitArkadasLise(liste:Set<String>){
        context.ds.edit {
            it[ARKADAS_LISTE_KEY] = liste
        }
    }
    suspend fun okuArkadasListe():Set<String>?{
        val p = context.ds.data.first()
        return p[ARKADAS_LISTE_KEY]
    }

    suspend fun kayidSayac(sayac:Int){
        context.ds.edit {
            it[SAYAC_KEY] = sayac
        }
    }

    suspend fun okuSayac():Int{
        val p = context.ds.data.first()
        return p[SAYAC_KEY]?:0
    }

}