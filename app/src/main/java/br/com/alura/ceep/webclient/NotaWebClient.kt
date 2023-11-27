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

    suspend fun salva(nota: Nota): Boolean {
        try {
            val resposta = notaService.salva(
                nota.id,
                NotaRequisicao(
                    titulo = nota.titulo,
                    descricao = nota.descricao,
                    imagem = nota.imagem
                )
            )
            return resposta.isSuccessful
        } catch (e: Exception) {
            Log.e(TAG, "salva: Nota não salva ${e.toString()}")
        }
        return false
    }

    suspend fun remove(id: String): Boolean {
        try {
            val resposta = notaService.remove(id)
            return resposta.isSuccessful
        } catch (e: Exception) {
            Log.e(TAG, "remove: Nota não removida...", )
        }
        return false
    }

}
