package com.viktoriagavrosh.englishsimulator.fake.repositories

import com.viktoriagavrosh.englishsimulator.data.StatisticRepository
import com.viktoriagavrosh.englishsimulator.model.Statistic
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.Month
import com.viktoriagavrosh.englishsimulator.ui.features.statistic.elements.getMonthByNumber
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import com.viktoriagavrosh.englishsimulator.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStatisticRepository(
    private var requestResult: RequestResult<List<Statistic>>
) : StatisticRepository {

    override suspend fun updateWordScore(date: String) {
        val newResult = requestResult.map { list ->
            val index = list.indexOfFirst { it.date == date }
            val oldStatistic = list[index]
            val newList = list.toMutableList()
            newList[index] = oldStatistic.copy(wordScore = oldStatistic.wordScore + 1)
                newList
        }
        requestResult = newResult
    }

    override suspend fun updateTranslateScore(date: String) {
        val newResult = requestResult.map { list ->
            val index = list.indexOfFirst { it.date == date }
            val oldStatistic = list[index]
            val newList = list.toMutableList()
            newList[index] = oldStatistic.copy(translateScore = oldStatistic.translateScore + 1)
            newList
        }
        requestResult = newResult
    }

    override suspend fun updateStatistic(statistic: Statistic) {
        val newResult = requestResult.map { list ->
            val index = list.indexOf(list.first { it.date == statistic.date })
            val newList = list.toMutableList()
            newList[index] = statistic
            newList
        }
        requestResult = newResult
    }

    override suspend fun updateIssueScore(date: String) {
        val newResult = requestResult.map { list ->
            val index = list.indexOfFirst { it.date == date }
            val oldStatistic = list[index]
            val newList = list.toMutableList()
            newList[index] = oldStatistic.copy(issueScore = oldStatistic.issueScore + 1)
            newList
        }
        requestResult = newResult
    }

    override suspend fun updateDialogScore(date: String) {
        val newResult = requestResult.map { list ->
            val index = list.indexOfFirst { it.date == date }
            val oldStatistic = list[index]
            val newList = list.toMutableList()
            newList[index] = oldStatistic.copy(dialogScore = oldStatistic.dialogScore + 1)
            newList
        }
        requestResult = newResult
    }

    override suspend fun updateDate(date: String) {
        TODO("Not yet implemented")
    }

    override fun getDate(): Flow<RequestResult<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertStatistic(statistic: Statistic) {
        val newResult = requestResult.map { list ->
            val newList = list.toMutableList()
            newList.add(statistic)
            newList.toList()
        }
        requestResult = newResult
    }

    override fun getStatisticByDate(date: String): Flow<RequestResult<List<Statistic>>> {
        val newResult = requestResult.map { list ->
            list.filter { it.date == date }
        }
        return if (newResult.data?.isEmpty() != false) {
            flow { emit(RequestResult.Error()) }
        } else {
            flow { emit(newResult) }
        }
    }

    override fun getAllStatisticsByMonth(month: Month): Flow<RequestResult<List<Statistic>>> {
        val wordsResult = requestResult.map { list ->
            list.filter { it.date.substring(3, 5).toInt() == month.ordinal }
        }
        return flow { emit(wordsResult) }
    }

    override fun getAllMonths(): Flow<RequestResult<List<Month>>> {
        val months = requestResult.map { list ->
            list.map { it.date.substring(3, 5) }
                .distinct()
                .map { getMonthByNumber(it.toInt()) }
        }
        return flow { emit(months) }
    }

    override suspend fun deleteAllStatisticsByMonth(monthNumber: String) {
        val newResult = requestResult.map { list ->
            val newList = list.toMutableList()
            newList.removeAll { it.date.substring(3, 5) == monthNumber }
            newList.toList()
        }
        requestResult = newResult
    }
}
