package br.com.alura.ceep.webclient

import br.com.alura.ceep.webclient.services.NotaService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInicializador {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080/")
        .addConverterFactory(MoshiConverterFactory.create()) // Add converter factory for serialization and deserialization of objects.
        .build()

    val notaService: NotaService = retrofit.create(NotaService::class.java)

}