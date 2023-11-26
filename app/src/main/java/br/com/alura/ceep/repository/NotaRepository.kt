package br.com.alura.ceep.repository

import br.com.alura.ceep.database.dao.NotaDao
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.NotaWebClient
import br.com.alura.ceep.webclient.model.NotaResposta
import kotlinx.coroutines.flow.Flow

class NotaRepository(
    private val dao: NotaDao,
    private val webClient: NotaWebClient
) {

    fun buscaTodas(): Flow<List<Nota>> {
        return dao.buscaTodas()
    }

    suspend fun atualizaTodas() {
        webClient.buscaTodas()?.let {
            dao.save(it)
        }
    }

    fun buscaPorId(id: String): Flow<Nota> {
        return dao.buscaPorId(id)
    }

    suspend fun remove(id: String) {
        dao.remove(id)
    }

    suspend fun salva(nota: Nota) {
        dao.salva(nota)
        webClient.salva(nota)
    }

}