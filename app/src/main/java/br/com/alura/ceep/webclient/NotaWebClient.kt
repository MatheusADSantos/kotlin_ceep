package br.com.alura.ceep.webclient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.model.NotaRequisicao
import br.com.alura.ceep.webclient.services.NotaService

class NotaWebClient {

    private val TAG: String? = "NotaWebClient"
    private val notaService: NotaService = RetrofitInicializador().notaService

    suspend fun buscaTodas(): List<Nota>? {
        return try {
            val notasResposta = notaService.buscaTodas()
            return notasResposta.map { notaResposta -> notaResposta.nota }
        } catch (e: Exception) {
            Log.e(TAG, "buscaTodas: ${e.message}")
            null
        }
    }

    suspend fun salva(nota: Nota) {
        try {
            val resposta = notaService.salva(
                nota.id,
                NotaRequisicao(
                    titulo = nota.titulo,
                    descricao = nota.descricao,
                    imagem = nota.imagem
                )
            )
            if (resposta.isSuccessful) {
                Log.i(TAG, "salva: Nota salva com sucesso!")
            } else {
                Log.i(TAG, "salva: Nota não salva...")
            }
        } catch (e: Exception) {
            Log.e(TAG, "salva: Nota não salva ${e.toString()}")
        }
    }

}
