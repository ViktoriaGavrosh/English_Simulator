package com.viktoriagavrosh.englishsimulator.data

import com.viktoriagavrosh.englishsimulator.model.Question
import com.viktoriagavrosh.englishsimulator.utils.RequestResult
import kotlinx.coroutines.flow.Flow

/**
 * Manager to retrieve items from repositories
 */
interface QuestionManager {

    /**
     * Retrieve all items from given source
     *
     * @return flow of [RequestResult] with list [Question]
     */
    fun getAllItems(): Flow<RequestResult<List<Question>>>

    /**
     * Retrieve all items from database by theme
     *
     * @param theme theme of words
     * @return flow of [RequestResult] with list [Question]
     */
    fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<Question>>>
}

/**
 * Manager to retrieve items from repositories
 *
 * @param repository instance of [GameRepository]
 */
class LocalQuestionManager(
    private val repository: GameRepository,
) : QuestionManager {
    /**
     * Retrieve all [Question] from repository
     *
     * @return flow of [RequestResult] with list [Question]
     */
    override fun getAllItems(): Flow<RequestResult<List<Question>>> {
        return when (repository) {
            is TranslateRepository -> repository.getAllSentences()
            is IssueRepository -> repository.getAllIssues()
            is DialogRepository -> repository.getAllDialogs()
            is WordRepository -> repository.getAllWords()
            else -> throw IllegalArgumentException(
                "Repository doesn't add to fun getAllItems in GetQuestionsUseCase"
            )
        }
    }

    /**
     * Retrieve all [Question] from database by theme
     *
     * @param theme theme of words
     * @return flow of [RequestResult] with list [Question]
     */
    override fun getAllItemsByTheme(theme: String): Flow<RequestResult<List<Question>>> {
        return when (repository) {
            is TranslateRepository -> repository.getAllSentences()
            is IssueRepository -> repository.getAllIssuesByTheme(theme = theme)
            is DialogRepository -> repository.getAllDialogs()
            is WordRepository -> repository.getAllWordsByTheme(theme = theme)
            else -> throw IllegalArgumentException(
                "Repository doesn't add to fun getAllItemsByTheme in GetQuestionsUseCase"
            )
        }
    }
}
