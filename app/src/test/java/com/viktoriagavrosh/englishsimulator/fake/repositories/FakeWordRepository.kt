package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.GameRepository
import com.viktoriagavrosh.englishsimulator.data.WordRepository
import com.viktoriagavrosh.englishsimulator.model.UiItem
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWordRepository(
    private var requestResult: RequestResult<List<UiItem>>
) : WordRepository, GameRepository {
    override fun getAllItems(): Flow<RequestResult<List<UiItem>>> {
        return flow { emit(requestResult) }
    }

    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<UiItem>>> {
        val wordsResult = requestResult.map { list ->
            list.filter { it.theme == theme }
        }
        return flow { emit(wordsResult) }
    }

    override fun getAllThemes(): Flow<RequestResult<List<String>>> {
        val themes = requestResult.map { list ->
            list.map { it.theme }.distinct()
        }
        return flow { emit(themes) }
    }

    override fun getWordById(id: Int): Flow<RequestResult<UiItem>> {
        val word = try {
            requestResult.map { list ->
                list.first { it.id == id }
            }
        } catch (e: NoSuchElementException) {
            RequestResult.Error()
        }

        return flow { emit(word) }
    }

    override suspend fun insertWord(word: UiItem) {
        val newResult = requestResult.map { list ->
            val newList = list.toMutableList()
            newList.add(word)
            newList.toList()
        }
        requestResult = newResult
    }

    override suspend fun updateWord(word: UiItem) {
        val newResult = requestResult.map { list ->
            val index = list.indexOf(list.first { it.id == word.id })
            val newList = list.toMutableList()
            newList[index] = word
            newList
        }
        requestResult = newResult
    }

    override suspend fun deleteWord(word: UiItem) {
        val newResult = requestResult.map { list ->
            val newList = list.toMutableList()
            newList.remove(word)
            newList.toList()
        }
        requestResult = newResult
    }
}
